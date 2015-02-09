package org.corenel.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipelineFactory;
import org.corenel.core.context.Context;
import org.corenel.services.helper.TestServiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:config/spring/context-*.xml"})
public class ServiceCRUDTest {
	
	@Resource(name = "serviceContext")
	private Context<String, Object> serviceContext;

	@Resource(name = "serviceHelperFactory")
	private ServiceHelperFactory serviceHelperFactory;
	 
	 /** CamelContext */
    @Resource(name = "camelContext")
    private CamelContext camelContext;

	@Before
	public void contextSetting(){
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createServiceHelperTest() throws Exception{
		
		serviceHelperFactory.createServiceHelper(TestServiceHelper.class);
		TestServiceHelper testHelper = serviceContext.getServiceHelperBean(TestServiceHelper.class.getName(), TestServiceHelper.class);
		
		Pipeline<List<Object>> pipeline = ServicePipelineFactory.newPipeline();
		pipeline.setServiceList(new ServiceHelper[]{testHelper});
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		producer.requestBody(ServiceExecutorType.Dispatcher.toString(), pipeline);
	}
	
	@Test
	public void removeServiceHelperTest() throws Exception{
		
		serviceContext.removeServiceHelperBean(TestServiceHelper.class.getName(), TestServiceHelper.class);
		TestServiceHelper testHelper = serviceContext.getServiceHelperBean(TestServiceHelper.class.getName(), TestServiceHelper.class);
		Assert.isNull(testHelper);
	}
	
	@After
	public void destory(){
		
		try {
			
			camelContext.stop();
			
		} catch (Exception e) {
		}
		
	}
	
}
