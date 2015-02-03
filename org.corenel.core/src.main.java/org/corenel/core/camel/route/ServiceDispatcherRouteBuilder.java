package org.corenel.core.camel.route;

import javax.annotation.Resource;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.corenel.core.camel.handler.CommandServiceHandlerResolver;
import org.corenel.core.camel.handler.ContextServiceHandlerResolver;
import org.corenel.core.context.Context;

@Component("serviceDispatcherRouteBuilder")
public final class ServiceDispatcherRouteBuilder extends RouteBuilder {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/** ServiceHelperFactory */
    @Resource(name = "serviceContext")
    private Context<String, Object> serviceContext;

	@Override
	public void configure() throws Exception {
			
		from("direct:service:pipeline")
		.setExchangePattern(ExchangePattern.InOut)
		.process(new ContextServiceHandlerResolver(serviceContext))
		.end();
		
		from("seda:service:pipeline")
		.setExchangePattern(ExchangePattern.InOut)
		.threads()
		.process(new ContextServiceHandlerResolver(serviceContext))
		.end();

		/*from("direct:service:excel:pipe:message")
			.setExchangePattern(ExchangePattern.InOut)
			.process(new ContextServiceHandlerResolver(serviceContext))
			.choice()
				.when(simple("${header.status} == 'F'"))
				.process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						return;
					}
				})
				.otherwise()
					.to("direct:service:message")
					.end();*/
		
		from("seda:service:jms")
			.setExchangePattern(ExchangePattern.InOut)
			.dynamicRouter(method(new CommandServiceHandlerResolver(),"resolve"))
			.end();
	}
}