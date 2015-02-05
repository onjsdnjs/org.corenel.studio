package org.corenel.services;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipelineFactory;
import org.corenel.core.context.Context;
import org.corenel.services.batch.helper.DefaultBatchServiceHelper;
import org.corenel.services.file.helper.DefaultFileServiceHelper;
import org.corenel.services.ftp.helper.DefaultFtpServiceHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:config/spring/context-*.xml"})
public class ServiceRouteTest {
	
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
	
	@Test
	public void routeServiceChainTest() throws CloneNotSupportedException{
		
		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		DefaultFileServiceHelper fileServiceHelper = serviceContext.getServiceHelperBean(DefaultFileServiceHelper.class.getName(), DefaultFileServiceHelper.class);
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		
		Pipeline pipeline = ServicePipelineFactory.newPipeline();
		pipeline.addServiceHelperChain(batchServiceHelper);
		pipeline.addServiceHelperChain(fileServiceHelper);
		pipeline.addServiceHelperChain(ftpServiceHelper);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		producer.requestBody("direct:service:pipeline", pipeline);
		
	}
	
}
