package org.corenel.core.camel.handler;

import org.apache.camel.Exchange;

import org.corenel.core.message.CommandMessage;

public class ServiceActiveMQHandlerResolver implements ServiceHandlerResolver{
	
	@Override
	public void resolve(Exchange exchange) throws Exception {
		
		CommandMessage commandMessage = (CommandMessage)exchange.getIn().getBody();
		
		System.out.println(commandMessage);

	}
}
