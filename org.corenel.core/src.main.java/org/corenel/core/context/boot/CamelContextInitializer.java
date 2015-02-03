package org.corenel.core.context.boot;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.corenel.core.camel.route.ServiceDispatcherRouteBuilder;
import org.corenel.core.camel.route.SeviceBootRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component("camelContextInitializer")
public class CamelContextInitializer implements ComponentInitializer, DisposableBean {

	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Resource(name = "camelContext")
    private CamelContext camelContext;
    
    @Resource(name = "seviceBootRouteBuilder")
    private SeviceBootRouteBuilder seviceBootRouteBuilder;

    @Resource(name = "serviceDispatcherRouteBuilder")
    private ServiceDispatcherRouteBuilder serviceDispatcherRouteBuilder;
    
	@Override
	public void initialize() throws Exception {
		logger.info(">> CamelContextInitializer initialize().. ");
		
		/*ActiveMQComponent activeMQComponent = (ActiveMQComponent) applicationContext.getBean("activemq");
        camelContext.addComponent("activemq", activeMQComponent);
		
        camelContext.addRoutes(new ActiveMQRouteBuilder());*/
        
		camelContext.addRoutes(seviceBootRouteBuilder);
		camelContext.addRoutes(serviceDispatcherRouteBuilder);
		camelContext.start();
		
	}

	@Override
	public void destroy() throws Exception {
		camelContext.stop();
	}

    public CamelContext getCamelContext() {
        return camelContext;
    }
}
