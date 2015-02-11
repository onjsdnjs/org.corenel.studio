package org.corenel.core.camel.route;

import javax.annotation.Resource;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.corenel.core.camel.handler.ServiceBootHandlerResolver;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.factory.ServiceHelperFactory;

@Component("seviceBootRouteBuilder")
public final class SeviceBootRouteBuilder extends RouteBuilder {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/** ServiceHelperFactory */
    @Resource(name = "serviceHelperFactory")
    private ServiceHelperFactory serviceHelperFactory;

	@Override
	public void configure() throws Exception {
		
		logger.info(">> SeviceBootRouteBuilder configure().. ");	
		
		from(ServiceExecutorType.Boot.getRoute())
			.setExchangePattern(ExchangePattern.InOut)
			.process(new ServiceBootHandlerResolver(serviceHelperFactory))
			.end();
	}
}