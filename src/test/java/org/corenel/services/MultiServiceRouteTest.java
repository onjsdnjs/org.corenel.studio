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
import org.corenel.core.disruptor.handler.ServiceDispatcherHandler;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper;
import org.corenel.services.batch.helper.DefaultBatchServiceHelper;
import org.corenel.services.disruptor.handler.chain.SecondDispatcherHandler1;
import org.corenel.services.disruptor.handler.chain.ThirdDispatcherHandler1;
import org.corenel.services.disruptor.handler.chain.FourthDispatcherHandler;
import org.corenel.services.file.helper.DefaultFileServiceHelper;
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
public class MultiServiceRouteTest {
	
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
	
	@Test
	public void routeServiceChainTest() throws Exception{
		
		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		DefaultFileServiceHelper fileServiceHelper = serviceContext.getServiceHelperBean(DefaultFileServiceHelper.class.getName(), DefaultFileServiceHelper.class);
		
		Pipeline pipeline = ServicePipelineFactory.newPipeline();
		pipeline.attachServiceHelperChain(batchServiceHelper);
		pipeline.attachServiceHelperChain(ftpServiceHelper);
		pipeline.attachServiceHelperChain(fileServiceHelper);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Pipeline result = producer.requestBody("direct:service:pipeline", pipeline, Pipeline.class);
		
		logger.info(result.getResult().getMessage().toString());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void dispatcherHandlerChainTest() throws Exception{
		
		ServiceDispatcherHandler<ServiceHelperHolder<ServiceHelper>> firstHandler = new ServiceDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		SecondDispatcherHandler1<ServiceHelperHolder<ServiceHelper>> secondHandler = new SecondDispatcherHandler1<ServiceHelperHolder<ServiceHelper>>();
		ThirdDispatcherHandler1<ServiceHelperHolder<ServiceHelper>> currentHandlerAfterSkip = new ThirdDispatcherHandler1<ServiceHelperHolder<ServiceHelper>>();
		FourthDispatcherHandler<ServiceHelperHolder<ServiceHelper>> nextHandler = new FourthDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{ firstHandler }); // current
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{ secondHandler });// current
		eventHandlerChain.setAfterEventHandlers(new EventHandler[]{ secondHandler }); // skip
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{ currentHandlerAfterSkip });// current
		eventHandlerChain.setNextEventHandlers(new EventHandler[]{ nextHandler });// next
		

		DefaultDisruptorServiceHelper disruptorServiceHelper = serviceContext.getBean(DefaultDisruptorServiceHelper.class.getName(), DefaultDisruptorServiceHelper.class);
		disruptorServiceHelper.getDisruptorExecutor().setEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});

		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		
		Pipeline pipeline = ServicePipelineFactory.newPipeline();
		pipeline.attachServiceHelperChain(batchServiceHelper);
		pipeline.attachServiceHelperChain(ftpServiceHelper);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Pipeline result = producer.requestBody("direct:service:pipeline", pipeline, Pipeline.class);
		
		logger.info(result.getResult().getMessage().toString());
		
	}
	
	@After
	public void destory(){
		
		try {
			
			camelContext.stop();
			
		} catch (Exception e) {
		}
		
	}
	
}
