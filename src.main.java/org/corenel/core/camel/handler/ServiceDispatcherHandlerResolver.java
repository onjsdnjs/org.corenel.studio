package org.corenel.core.camel.handler;

import java.util.Queue;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang.Validate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.executor.DefaultDisruptorExecutor;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("ServiceDispatcherHandlerResolver process()..");
		
		serviceContext.putBean(ApplicationConstants.EXCHANGE, exchange);
		disruptorHelper = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		DefaultDisruptorExecutor<ServiceHelperHolder<ServiceHelper>> disruptorExecutor = disruptorHelper.getDisruptorExecutor();

		Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
		Validate.notNull(pipeline);
		
		switch(pipeline.getServiceDispatcherType()){
			
			//calling service by client request : default
			case requestService:
				
				ServiceHelper[] serviceHelpers = pipeline.getServiceList();
				serviceContext.putBean(ApplicationConstants.REQUEST_SERVICE, serviceHelpers);
				
				disruptorExecutor.start();
				disruptorHelper.handleService();
				disruptorExecutor.awaitAndShutdown(10000);
				logger.info("Disruptor has shutDown().");
				break;

			//calling service in background
			case daemonService :

				ServiceHelper serviceHelper = (ServiceHelper)pipeline.detachServiceChain();
				serviceContext.putBean(ApplicationConstants.DAEMON_SERVICE, serviceHelper);
				EventHandlerChain[] eventHandlerChain = serviceHelper.getEventHandlerChain();
				
				if(eventHandlerChain != null){
					disruptorExecutor.setEventHandlerChain(eventHandlerChain);
				}
				
				disruptorExecutor.start();
				disruptorHelper.handleService();
				disruptorExecutor.setEventHandlerChain(null);

				Queue<ServiceHelper> serviceQueue = pipeline.getServiceQueue();
				if(!serviceQueue.isEmpty()){
					ProducerTemplate producer = exchange.getContext().createProducerTemplate();
					producer.requestBody(ServiceExecutorType.Dispatcher.getRoute(), pipeline);
				}
		}
	}
}
