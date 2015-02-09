package org.corenel.core.camel.route;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;

import org.corenel.core.camel.handler.ServiceActiveMQHandlerResolver;

public final class ActiveMQRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
			
	from("activemq:queue:command:service")
		.setExchangePattern(ExchangePattern.InOut)
		.threads()
		.dynamicRouter(method(ServiceActiveMQHandlerResolver.class, "resolve"))
		.to("direct:command:service")
		.end();
	}
}