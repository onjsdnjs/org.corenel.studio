package org.corenel.core.disruptor.executor;


import java.util.concurrent.Executors;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;

import org.corenel.core.disruptor.NamedThreadFactory;
import org.corenel.core.disruptor.WaitStrategyType;
import org.corenel.core.disruptor.exception.DisruptorExceptionHandler;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.core.disruptor.manager.AbstractDisruptorLifecycleManager;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

@SuppressWarnings("serial")
public abstract class AbstractDisruptorExecutor<T> extends AbstractDisruptorLifecycleManager<T> implements DisruptorExecutor<T>  {

	private int ringBufferSize = 1024;
	private ProducerType producerType = ProducerType.SINGLE;
	private WaitStrategyType waitStrategyType = WaitStrategyType.BLOCKING;
	private EventHandlerChain<T>[] eventHandlerChain;
	
	private EventFactory<T> eventFactory;
	
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
	public void init(){
		
		/*Validate.notNull(getThreadName());
		Validate.notNull(getEventFactory());*/
		
		createThreadExecutor();
		configureDisruptor();
		
		disruptorExceptionHandler();
		disruptorEventHandler();
		
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
	
	@Override
	public void disruptorEventHandler() {
		Validate.notEmpty(eventHandlerChain, "Define a Event Handler Chain.");
		disruptorEventHandlerChain();
	}
	
	@Override
	public void disruptorExceptionHandler(){
		getDisruptor().handleExceptionsWith(new DisruptorExceptionHandler(getThreadName()));
	}
	
	public void setEventHandlerChain(EventHandlerChain<T>[] eventHandlerChain) {
		this.eventHandlerChain = eventHandlerChain;
	}
	
	private void disruptorEventHandlerChain(){
		
		for(int i=0;i<eventHandlerChain.length;i++){
		
			EventHandlerChain<T> eventHandlersChain = eventHandlerChain[i];
			EventHandlerGroup<T> eventHandlerGroup = null;
			
			if(! ArrayUtils.isEmpty(eventHandlersChain.getCurrentEventHandlers())){
				eventHandlerGroup = getDisruptor().handleEventsWith(eventHandlersChain.getCurrentEventHandlers());
				
			}
			
			if(! ArrayUtils.isEmpty(eventHandlersChain.getAfterEventHandlers())){
				eventHandlerGroup = getDisruptor().after(eventHandlersChain.getAfterEventHandlers());
				
			}
			
			if(eventHandlerGroup != null){
				if(! ArrayUtils.isEmpty(eventHandlersChain.getNextEventHandlers())){
					eventHandlerGroup.then(eventHandlersChain.getNextEventHandlers());
				}
			}
		}
		
		getEventProcessorGraph();
	}
	
	public String getEventProcessorGraph(){
		
		StringBuilder str = new StringBuilder();
		
		for(int i=0;i<eventHandlerChain.length;i++){
			str.append("\n");
			str.append(eventHandlerChain[i].printDependencyGraph());
		}
		
		System.out.println(str.toString());
		
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
	public abstract void publish(EventTranslator<T> eventTranslator); 
}
