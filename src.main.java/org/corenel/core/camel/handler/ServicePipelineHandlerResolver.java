package org.corenel.core.camel.handler;


import java.util.Queue;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang.Validate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicePipelineHandlerResolver implements Processor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	private Context<String, Object> serviceContext;
	private DefaultDisruptorServiceHelper disruptorHelper;
	
	public ServicePipelineHandlerResolver(Context<String, Object> context) {
		serviceContext = context;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("PipelineServiceHandlerResolver process()..");

		Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
		Validate.notNull(pipeline);
		ServiceHelper serviceHelper = pipeline.detachServiceHelperChain();
		serviceContext.putBean(ApplicationConstants.EXCHANGE, exchange);
		serviceContext.putBean(ApplicationConstants.ROUTE_PROCESS, serviceHelper);

		disruptorHelper = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		disruptorHelper.getDisruptorExecutor().start();
		disruptorHelper.handleService();
		
		/**
		 * Disruptor RingBuffer Instance is not singleton
		 * */
		disruptorHelper.getDisruptorExecutor().awaitAndShutdown(10000);
		logger.info("Disruptor has shutDown().");
		
		Queue<ServiceHelper> serviceQueue = pipeline.getServiceQueue();
		if(!serviceQueue.isEmpty()){
			ProducerTemplate producer = exchange.getContext().createProducerTemplate();
			producer.requestBody("direct:service:pipeline", pipeline);
		}
	}
}
