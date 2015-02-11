package org.corenel.core.camel.route;

import javax.annotation.Resource;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.StopProcessor;
import org.corenel.core.camel.handler.ServiceDispatcherHandlerResolver;
import org.corenel.core.common.domain.Response;
import org.corenel.core.common.domain.ServiceResponse;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings("rawtypes")
@Component("serviceDispatcherRouteBuilder")
public final class ServiceExecutorRouteBuilder extends RouteBuilder {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Resource(name = "serviceContext")
    private Context<String, Object> serviceContext;

	@Override
	public void configure() throws Exception {
			
		onException(Exception.class).process(new StopProcessor() {
					
			@Override
			public boolean process(Exchange exchange, AsyncCallback callback){

				exchange.setProperty("CamelRouteStop", Boolean.TRUE);

				Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
				Exception exception = (Exception)exchange.getOut().getBody();
				
				Response response = new ServiceResponse();
				response.setMessage(exception.getMessage());
				pipeline.setResult(response);
				exchange.getOut().setBody(pipeline);

				callback.done(true);
				
				return true;
			}
		})
		.end();

		from(ServiceExecutorType.Dispatcher.getRoute())
		.setExchangePattern(ExchangePattern.InOut)
		.process(new ServiceDispatcherHandlerResolver(serviceContext))
		.choice()
				.when(simple("${header.status} == 'F'"))
				.process(new StopProcessor() {
					
					@Override
					public boolean process(Exchange exchange, AsyncCallback callback){
					
						exchange.setProperty("CamelRouteStop", Boolean.TRUE);

						Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
						Response response = new ServiceResponse();
						response.setMessage("ftp : 파일 업로드에 실패했습니다.");
						pipeline.setResult(response);
						exchange.getOut().setBody(pipeline);
						
						callback.done(true);
						return true;
					}
				})
				.end();
		
		/*from("seda:service:pipeline")
		.setExchangePattern(ExchangePattern.InOut)
		.threads()
		.process(new ContextServiceHandlerResolver(serviceContext))
		.end();

		from("seda:service:jms")
			.setExchangePattern(ExchangePattern.InOut)
			.dynamicRouter(method(new CommandServiceHandlerResolver(),"resolve"))
			.end();*/
	}
}