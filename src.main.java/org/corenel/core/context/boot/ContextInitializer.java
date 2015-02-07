package org.corenel.core.context.boot;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("coreContextInitializer")
public class ContextInitializer implements Initializer, ApplicationContextAware, InitializingBean {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "serviceContext")
    private Context<String, Object> serviceContext;

	@Resource(name = "camelContext")
    private CamelContext camelContext;

	private ApplicationContext applicationContext;
	
	private ConcurrentHashMap<String, CamelContext> camelContextMap;

	@Override
	public void initialize() throws Exception {

		/**
		 * Camel Context , Components initialize
		 */
		Map<String, ComponentInitializer> componentInitializerMap = applicationContext.getBeansOfType(ComponentInitializer.class);
		Set<Entry<String, ComponentInitializer>> componentEntrySet = componentInitializerMap.entrySet();
		
		for (Entry<String, ComponentInitializer> initializer : componentEntrySet) {
			initializer.getValue().initialize();
		}
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		producer.sendBody("direct:service:boot", serviceContext);
		
		logger.info(">> serviceContextBoot(). ");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public ConcurrentHashMap<String, CamelContext> getCamelContextMap() {
		return camelContextMap;
	}

	public void setCamelContextMap( ConcurrentHashMap<String, CamelContext> camelContextMap) {
		this.camelContextMap = camelContextMap;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}
}
