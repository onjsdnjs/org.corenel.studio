package org.corenel.services;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipelineFactory;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;
import org.corenel.services.batch.helper.DefaultBatchServiceHelper;
import org.corenel.services.disruptor.handler.chain.InterWorkingComponentHandler;
import org.corenel.services.ftp.helper.DefaultFtpServiceHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lmax.disruptor.EventHandler;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:config/spring/context-*.xml"})
public class ComponentInterWorkingTest {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "serviceContext")
	private Context<String, Object> serviceContext;

	@Resource(name = "serviceHelperFactory")
	private ServiceHelperFactory serviceHelperFactory;
	 
    @Resource(name = "camelContext")
    private CamelContext camelContext;

	@Before
	public void contextSetting(){
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void componentInterWorkingTest() throws Exception {
		
		InterWorkingComponentHandler<ServiceHelperHolder<ServiceHelper>> interWorkingHandler = new InterWorkingComponentHandler<ServiceHelperHolder<ServiceHelper>>();
		
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{ interWorkingHandler});

		DefaultDisruptorServiceHelper disruptorServiceHelper = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		disruptorServiceHelper.getDisruptorExecutor().setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});

		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		
		/**
		 * ������ �Ϻ��ϰ� �����ϸ� ������ Disruptor ���� �����Ǿ����Ƿ� �����̳� �޸� �������鿡�� �� �� ���� ����̶� �� �� �ֽ��ϴ�.
		 * EventHandlerChain �� ����Ͽ� ������ ����� ������ �� ������ �� ����� �� �� ��Ȯ�ϰ� �������̶� �� �� �ֽ��ϴ�.
		 * �� camel �� ����� ����� ���� ���� ������ �ƴ϶� ������ Disruptor �ȿ��� �̺�Ʈ �ۺ��� ���� ���� ���� ����̶� �� �� �ְڽ��ϴ�. 
		 * */
		Pipeline pipeline = ServicePipelineFactory.newPipeline();
		pipeline.setServiceList(new ServiceHelper[]{batchServiceHelper,ftpServiceHelper});
		pipeline.setInterWorking(true);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Pipeline result = producer.requestBody("direct:service:pipeline", pipeline, Pipeline.class);
		
		logger.info(result.getResult().getMessage().toString());
		
	}
	
	@After
	public void destory(){
		
		try {
			
//			camelContext.stop();
			
		} catch (Exception e) {
		}
		
	}
	
}
