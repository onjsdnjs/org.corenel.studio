package org.corenel.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.helper.ServiceHelper;
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void excelUploadTest() throws CloneNotSupportedException{
		
		ProductVO productVO = new ProductVO();
		productVO.setMid("testcorp");
		String[] fieldNames = {"mid", "productid", "productkind", "productnm", "amount", "useflag", "enteruser"};
		String[] fieldValues = {productVO.getMid()};
    	
    	DefaultExcelUpLoadServiceHelper excelServiceHelper = (DefaultExcelUpLoadServiceHelper)serviceContext.getServiceHelperBean(DefaultExcelUpLoadServiceHelper.class.getName(), DefaultExcelUpLoadServiceHelper.class);
    	
    	excelServiceHelper.setColumnKey(ApplicationConstants.VALID_PRODUCT);
    	excelServiceHelper.setExcelVO(productVO);
    	excelServiceHelper.setFieldNames(fieldNames);
    	excelServiceHelper.setFieldValues(fieldValues);
    	
    	Pipeline<List<Object>> pipeline = ServicePipelineFactory.newPipeline();
		pipeline.setServiceList(new ServiceHelper[]{excelServiceHelper});
    	
    	ProducerTemplate producer = camelContext.createProducerTemplate();
    	producer.requestBody(ServiceExecutorType.Dispatcher.toString(), pipeline);
		
	}
	
}
