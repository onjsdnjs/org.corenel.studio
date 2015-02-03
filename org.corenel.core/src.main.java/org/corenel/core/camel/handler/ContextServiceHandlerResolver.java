package org.corenel.core.camel.handler;

import java.util.concurrent.BlockingQueue;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;

public class ContextServiceHandlerResolver implements Processor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	private Context<String, Object> serviceContext;
	
	public ContextServiceHandlerResolver(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		logger.info("ContextServiceHandlerResolver process()..");
		
		Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
		ServiceHelper dettachService = pipeline.dettachServiceChain();
		serviceContext.putBean(ApplicationConstants.SERVICE_CLASS_TYPE, dettachService);
		
		logger.info("ServiceHelper:{}", dettachService.getClass().getName());
		
		DefaultDisruptorServiceHelper disruptor = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		disruptor.handleService();
		
		BlockingQueue<ServiceHelper> serviceQueue = pipeline.getServiceQueue();
		if(!serviceQueue.isEmpty()){
			ProducerTemplate producer = exchange.getContext().createProducerTemplate();
			producer.requestBody("direct:service:pipeline", pipeline);
		}
		
		disruptor.getDisruptorExecutor().awaitAndShutdown(10000);

		logger.info("Disruptor handleService()..");
		
	}
}
