package org.corenel.core.disruptor.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.executor.DefaultDisruptorExecutor;
import org.corenel.core.disruptor.factory.DefaultEventFactory;
import org.corenel.core.disruptor.handler.DispatcherHandler;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;

import com.lmax.disruptor.EventHandler;

/**

 * @author Á¤¼ö¿ø
 */

@SuppressWarnings({"unchecked", "serial"})
public abstract class AbstractDisruptorServiceHelper extends GenericServiceHelper  implements DisruptorServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>> disruptorExecutor;
	
	public AbstractDisruptorServiceHelper(Context<String, Object> context) {
		
		super(context);
		
		logger.info("Disruptor initialize..");
		
		disruptorExecutor = new DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>>();
		
		DispatcherHandler<ServiceHelperHolder<ServiceHelper>> handler = new DispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{handler});

		getDisruptorExecutor().setEventFactory(new DefaultEventFactory<ServiceHelperHolder<ServiceHelper>>(ServiceHelperHolder.class));
		getDisruptorExecutor().setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});
		getDisruptorExecutor().init();
	}
	
	public DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>> getDisruptorExecutor() {
		return disruptorExecutor;
	}

	@Override
	public void handleService() throws Exception {
		
		getDisruptorExecutor().setThreadName(getContext().getBean(ApplicationConstants.SERVICE_CLASS_TYPE, ServiceHelper.class).getClass().getName());
		publishEvent();
	}
	
	@Override
	public void executeService() throws Exception{
		
	}
}
