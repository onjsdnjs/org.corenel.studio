package org.corenel.core.disruptor.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.publisher.DefaultEventPublisherOneArg;
import org.corenel.core.disruptor.publisher.EventPublisher;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial"})
public class DefaultDisruptorServiceHelper extends AbstractDisruptorServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public DefaultDisruptorServiceHelper(Context<String, Object> context) {
		super(context);
	}
	
	@Override
	public void publishEvent() throws Exception{
		
		ServiceHelper serviceHelper = getServiceContext().getBean(ApplicationConstants.SERVICE_CLASS_TYPE, ServiceHelper.class);
		EventPublisher<ServiceHelperHolder<ServiceHelper>> publisher = new DefaultEventPublisherOneArg<ServiceHelperHolder<ServiceHelper>,ServiceHelper>(getDisruptorExecutor());
		
		publisher.publish(serviceHelper);
	}
}
