package org.corenel.core.camel.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.Validate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ServiceType;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceDispatcherHandlerResolver implements Processor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	private Context<String, Object> serviceContext;
	private DefaultDisruptorServiceHelper disruptorHelper;
	
	public ServiceDispatcherHandlerResolver(Context<String, Object> context) {
		serviceContext = context;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("DispatcherServiceHandlerResolver process()..");
		
		serviceContext.putBean(ApplicationConstants.EXCHANGE, exchange);
		disruptorHelper = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		disruptorHelper.getDisruptorExecutor().start();

		Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
		Validate.notNull(pipeline);
		ServiceHelper[] serviceHelpers = pipeline.getServiceList();
		
		switch(pipeline.getServiceDispatcherType()){
			
			/**
			 * calling service by publishing disruptor event
			 * */
			case EventPublish:
				serviceContext.putBean(ApplicationConstants.EVENT_PUBLISH, serviceHelpers);
				disruptorHelper.handleService();
				break;

			/**
			 * calling service by camel process
			 * */
			case RouteProcess :
				for (ServiceHelper serviceHelper : serviceHelpers) {
					serviceContext.putBean(ApplicationConstants.ROUTE_PROCESS, serviceHelper);
					disruptorHelper.handleService();
				}
		}
		
		/**
		 * Disruptor RingBuffer Instance is singleton
		 * */
		disruptorHelper.getDisruptorExecutor().awaitAndShutdown(10000);
		logger.info("Disruptor has shutDown().");
	}
}
