package org.corenel.core.camel.handler;


import java.util.Queue;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ServiceResponse;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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
		ServiceHelper serviceHelper = pipeline.dettachServiceHelperChain();
		DefaultDisruptorServiceHelper disruptor = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		
		if(!StringUtils.isEmpty(serviceHelper)){
			
			serviceContext.putBean(ApplicationConstants.SERVICE_CLASS_TYPE, serviceHelper);
			serviceContext.putBean(ApplicationConstants.EXCHANGE, exchange);
			
			logger.info("ServiceHelper:{}", serviceHelper.getClass().getName());
			
			disruptor.handleService();
		}
		
		Queue<ServiceHelper> serviceQueue = pipeline.getServiceQueue();
		if(!serviceQueue.isEmpty()){
			ProducerTemplate producer = exchange.getContext().createProducerTemplate();
			producer.requestBody("direct:service:pipeline", pipeline);

		}else{
			disruptor.getDisruptorExecutor().awaitAndShutdown(10000);
			logger.info("Disruptor has shutDown().");
		}
	}
}
