package org.corenel.services.test;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipeline;
import org.corenel.core.context.Context;
import org.corenel.services.batch.helper.DefaultBatchServiceHelper;
import org.corenel.services.excel.helper.impl.upload.DefaultExcelUpLoadServiceHelper;
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
	public void routeServiceOneTest() throws CloneNotSupportedException{
		
		ProductVO productVO = new ProductVO();
		
		String[] fieldNames = {"mid", "productid", "productkind", "productnm", "amount", "useflag", "enteruser"};
		String[] fieldValues = {productVO.getMid()};
    	
    	DefaultExcelUpLoadServiceHelper excelServiceHelper = (DefaultExcelUpLoadServiceHelper)serviceContext.getServiceBean(DefaultExcelUpLoadServiceHelper.class.getName(), DefaultExcelUpLoadServiceHelper.class);
    	
    	excelServiceHelper.setColumnKey(ApplicationConstants.VALID_PRODUCT);
    	excelServiceHelper.setExcelVO(productVO);
    	excelServiceHelper.setFieldNames(fieldNames);
    	excelServiceHelper.setFieldValues(fieldValues);
    	
    	Pipeline pipeline = new ServicePipeline();
		pipeline.attachServiceChain(excelServiceHelper);
    	
    	ProducerTemplate producer = camelContext.createProducerTemplate();
    	producer.requestBody("direct:service:default", pipeline);
		
	}

	@Test
	public void routeServiceChainTest() throws CloneNotSupportedException{
		
		DefaultBatchServiceHelper batchServiceHelper = (DefaultBatchServiceHelper)serviceContext.getServiceBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		DefaultFileServiceHelper fileServiceHelper = (DefaultFileServiceHelper)serviceContext.getServiceBean(DefaultFileServiceHelper.class.getName(), DefaultFileServiceHelper.class);
		DefaultFtpServiceHelper ftpServiceHelper = (DefaultFtpServiceHelper)serviceContext.getServiceBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		
		Pipeline pipeline = new ServicePipeline();
		pipeline.attachServiceChain(batchServiceHelper);
		pipeline.attachServiceChain(fileServiceHelper);
		pipeline.attachServiceChain(ftpServiceHelper);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		producer.requestBody("direct:service:pipeline", pipeline);
		
	}

	@Test
	public void addServiceTest() throws Exception{
		
		serviceHelperFactory.addServiceHelper(TestServiceHelper.class);
		TestServiceHelper testHelper = serviceContext.getBean(TestServiceHelper.class.getName(), TestServiceHelper.class);
		
		Pipeline pipeline = new ServicePipeline();
		pipeline.attachServiceChain(testHelper);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		producer.requestBody("direct:service:pipeline", pipeline);
		
	}
	
}
