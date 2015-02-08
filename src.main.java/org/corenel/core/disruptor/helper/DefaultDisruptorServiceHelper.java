package org.corenel.core.disruptor.helper;

import org.apache.camel.Exchange;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ServiceExecutorType;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.publisher.DefaultEventPublisherOneArg;
import org.corenel.core.disruptor.publisher.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		
		Exchange exchange = getServiceContext().getBean(ApplicationConstants.EXCHANGE, Exchange.class);
		Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
		EventPublisher<ServiceHelperHolder<ServiceHelper>> publisher = new DefaultEventPublisherOneArg<ServiceHelperHolder<ServiceHelper>,ServiceHelper>(getDisruptorExecutor());

		if(pipeline.getServiceExecutorType() == ServiceExecutorType.INTERWORKING){
			ServiceHelper[] serviceHelpers = getServiceContext().getBean(ApplicationConstants.INTERWORKING_CLASS_TYPE, ServiceHelper[].class);
			for (ServiceHelper serviceHelper : serviceHelpers) {
				publisher.publish(serviceHelper);
			}
		
		}else{
			ServiceHelper serviceHelper = getServiceContext().getBean(ApplicationConstants.SERVICE_CLASS_TYPE, ServiceHelper.class);
			publisher.publish(serviceHelper);
		}
		
	}
}
