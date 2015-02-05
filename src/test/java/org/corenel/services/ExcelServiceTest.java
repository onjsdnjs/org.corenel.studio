package org.corenel.services;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipelineFactory;
import org.corenel.core.context.Context;
import org.corenel.services.domain.ProductVO;
import org.corenel.services.excel.helper.impl.upload.DefaultExcelUpLoadServiceHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:config/spring/context-*.xml"})
public class ExcelServiceTest {
	
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
	public void excelUploadTest() throws CloneNotSupportedException{
		
		ProductVO productVO = new ProductVO();
		
		String[] fieldNames = {"mid", "productid", "productkind", "productnm", "amount", "useflag", "enteruser"};
		String[] fieldValues = {productVO.getMid()};
    	
    	DefaultExcelUpLoadServiceHelper excelServiceHelper = (DefaultExcelUpLoadServiceHelper)serviceContext.getServiceHelperBean(DefaultExcelUpLoadServiceHelper.class.getName(), DefaultExcelUpLoadServiceHelper.class);
    	
    	excelServiceHelper.setColumnKey(ApplicationConstants.VALID_PRODUCT);
    	excelServiceHelper.setExcelVO(productVO);
    	excelServiceHelper.setFieldNames(fieldNames);
    	excelServiceHelper.setFieldValues(fieldValues);
    	
    	Pipeline pipeline = ServicePipelineFactory.newPipeline();
		pipeline.attachServiceHelperChain(excelServiceHelper);
    	
    	ProducerTemplate producer = camelContext.createProducerTemplate();
    	producer.requestBody("direct:service:pipeline", pipeline);
		
	}
	
}
