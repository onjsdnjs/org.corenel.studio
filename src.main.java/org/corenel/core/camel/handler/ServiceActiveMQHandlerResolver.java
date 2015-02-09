package org.corenel.core.camel.handler;

import org.apache.camel.Exchange;
import org.corenel.core.message.CommandMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceActiveMQHandlerResolver implements ServiceHandlerResolver{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void resolve(Exchange exchange) throws Exception {
		
		logger.info("ServiceActiveMQHandlerResolver process()..");
		
		CommandMessage commandMessage = (CommandMessage)exchange.getIn().getBody();
		
		System.out.println(commandMessage);

	}
}
