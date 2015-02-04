package org.corenel.core.camel.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.context.Context;
import org.corenel.core.context.ServiceContext;

public class ServiceBootHandlerResolver implements Processor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private ServiceHelperFactory serviceHelperFactory;
	
	public ServiceBootHandlerResolver(ServiceHelperFactory serviceHelperFactory) {
		this.serviceHelperFactory = serviceHelperFactory;
	}

	@Override
	@SuppressWarnings({"unchecked" })
	public void process(Exchange exchange) throws Exception {
		
		logger.info(">> ServiceBootHandlerResolver process()..");
		
		Context<String, Object> context = (ServiceContext<String, Object>)exchange.getIn().getBody();
		serviceHelperFactory.createServiceHelper(context);
	}
}
