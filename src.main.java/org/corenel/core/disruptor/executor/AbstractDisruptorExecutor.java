package org.corenel.core.disruptor.executor;

import java.util.concurrent.Executors;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.NamedThreadFactory;
import org.corenel.core.disruptor.WaitStrategyType;
import org.corenel.core.disruptor.exception.DisruptorExceptionHandler;
import org.corenel.core.disruptor.handler.ServiceDispatcherHandler;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.core.disruptor.manager.AbstractDisruptorLifecycleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

@SuppressWarnings({"serial","unchecked"})
public abstract class AbstractDisruptorExecutor<T> extends AbstractDisruptorLifecycleManager<T> implements DisruptorExecutor<T>  {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private int ringBufferSize = 1024;
	private ProducerType producerType = ProducerType.SINGLE;
	private WaitStrategyType waitStrategyType = WaitStrategyType.BLOCKING;
	private EventFactory<T> eventFactory;
	private Context<String, Object> serviceContext;
	
	public AbstractDisruptorExecutor(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
	}

	protected RingBuffer<T> getRingBuffer(){
		return getDisruptor().getRingBuffer();
	}
	
	public long getCurrentLocation() {
		return getDisruptor().getCursor();
	}
	
	public long getRemainingCapacity() {
		return getRingBuffer().remainingCapacity();
	}
	
	public void resetRingbuffer(long sequence) {
		getRingBuffer().resetTo(sequence);
	}
	
	public void publishToRingbuffer(long sequence) {
		getRingBuffer().publish(sequence);;
	}
	
	public int getRingBufferSize() {
		return ringBufferSize;
	}

	public void setRingBufferSize(int ringBufferSize) {
		this.ringBufferSize = ringBufferSize;
	}

	public ProducerType getProducerType() {
		return producerType;
	}

	public void setProducerType(ProducerType producerType) {
		this.producerType = producerType;
	}

	public WaitStrategyType getWaitStrategyType() {
		return waitStrategyType;
	}

	public void setWaitStrategyType(WaitStrategyType waitStrategyType) {
		this.waitStrategyType = waitStrategyType;
	}

	protected EventFactory<T> getEventFactory() {
		return eventFactory;
	}

	public void setEventFactory(EventFactory<T> eventFactory) {
		this.eventFactory = eventFactory;
	}
	
	@Override
	public void start(){
		
		Validate.notNull(getEventFactory());
		
		createThreadExecutor();
		configureDisruptor();
		disruptorEventHandlerChain();
		disruptorExceptionHandler();
		getDisruptor().start();
	}
	
	private void configureDisruptor(){
		
		getDisruptorConfiguration();
		
		Disruptor<T> disruptorWrapper = new DisruptorWrapper<T>(getEventFactory(),
				getRingBufferSize(),
				getExecutor(),
				getProducerType(),
				getWaitStrategyType().instance());
		
		setDisruptor(disruptorWrapper);
		
	}
	
	private void createThreadExecutor() {
		super.setExecutor(Executors.newCachedThreadPool(new NamedThreadFactory(getThreadName())));
	}
	
	private boolean disruptorEventHandler() {
		
		boolean hasChain = false;
		EventHandlerChain<ServiceHelper>[] handlerChain = serviceContext.getBean(ApplicationConstants.EVENT_HANDLER_CHAIN + String.valueOf(Thread.currentThread().getId()), EventHandlerChain[].class);
		if(handlerChain != null){
			hasChain = true;
		};
		return hasChain;
	}
	
	@Override
	public void disruptorExceptionHandler(){
		getDisruptor().handleExceptionsWith(new DisruptorExceptionHandler(getThreadName()));
	}

	@Override
	public void setEventHandlerChain(EventHandlerChain<T>[] handlerChain) {
		serviceContext.putBean(ApplicationConstants.EVENT_HANDLER_CHAIN + String.valueOf(Thread.currentThread().getId()), handlerChain);
	}
	
	private String getThreadName() {
		return getClass().getSimpleName() + "_"+  String.valueOf(Thread.currentThread().getId());
	}
	
	public void disruptorEventHandlerChain(){
		
		if(!disruptorEventHandler()){
			
			ServiceDispatcherHandler<ServiceHelperHolder<ServiceHelper>> handler = new ServiceDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
			EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
			eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{handler});
			setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});
		}
		
		EventHandlerChain<T>[] eventHandlersChain = serviceContext.getBean(ApplicationConstants.EVENT_HANDLER_CHAIN + String.valueOf(Thread.currentThread().getId()), EventHandlerChain[].class);
		
		for(int i=0;i<eventHandlersChain.length;i++){
		
			EventHandlerGroup<T> eventHandlerGroup = null;
			EventHandlerChain<T> eventHandlerChain = eventHandlersChain[i];
			
			if(! ArrayUtils.isEmpty(eventHandlerChain.getCurrentEventHandlers())){
				eventHandlerGroup = getDisruptor().handleEventsWith(eventHandlerChain.getCurrentEventHandlers());
				
			}
			
			if(! ArrayUtils.isEmpty(eventHandlerChain.getAfterEventHandlers())){
				eventHandlerGroup = getDisruptor().after(eventHandlerChain.getAfterEventHandlers());
				
			}
			
			if(eventHandlerGroup != null){
				if(! ArrayUtils.isEmpty(eventHandlerChain.getNextEventHandlers())){
					eventHandlerGroup.then(eventHandlerChain.getNextEventHandlers());
				}
			}
		}
		
		getEventProcessorGraph();
	}
	
	public String getEventProcessorGraph(){
		
		StringBuilder str = new StringBuilder();
		
		EventHandlerChain<T>[] eventHandlersChain = serviceContext.getBean(ApplicationConstants.EVENT_HANDLER_CHAIN + String.valueOf(Thread.currentThread().getId()), EventHandlerChain[].class);
		for(int i=0;i<eventHandlersChain.length;i++){
			str.append("\n");
			str.append(eventHandlersChain[i].printEventChainFlow());
		}
		
		logger.info(str.toString());
		
		return str.toString();
	}
	
	public String getDisruptorConfiguration() {
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("Thread Name: ");
		str.append(getThreadName());
		str.append(" | ");
		str.append("Ringbuffer slot size: ");
		str.append(getRingBufferSize());
		str.append(" | ");
		str.append("Producer type: ");
		str.append(getProducerType());
		str.append(" | ");
		str.append("Wait strategy: ");
		str.append(getWaitStrategyType());
		str.append("}");
		return str.toString();
	}
	
	@Override
	public abstract void publish(EventTranslator<T> eventTranslator) throws Exception; 
}
