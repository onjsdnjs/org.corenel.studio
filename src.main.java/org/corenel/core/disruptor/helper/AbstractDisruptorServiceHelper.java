package org.corenel.core.disruptor.helper;


import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.executor.DefaultDisruptorExecutor;
import org.corenel.core.disruptor.factory.DefaultEventFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial"})
public abstract class AbstractDisruptorServiceHelper extends GenericServiceHelper  implements DisruptorServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>> disruptorExecutor;
	
	public AbstractDisruptorServiceHelper(Context<String, Object> context) {
		
		super(context);
		
		logger.info("Disruptor initialize..");
		
		disruptorExecutor = new DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>>(getServiceContext());
		disruptorExecutor.setEventFactory(new DefaultEventFactory<ServiceHelperHolder<ServiceHelper>>(ServiceHelperHolder.class));
		
	}
	
	public DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>> getDisruptorExecutor() {
		return disruptorExecutor;
	}

	@Override
	public void handleService() throws Exception {
		
		publishEvent();
	}
	
	@Override
	public void executeService() throws Exception{
		
	}
}
