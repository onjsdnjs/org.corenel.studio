package org.corenel.services;

import java.util.List;
import javax.annotation.Resource;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.corenel.core.common.domain.ServiceType.ServiceDispatcherType;
import org.corenel.core.common.domain.ServiceType.ServiceExecutorType;
import org.corenel.core.common.factory.ServiceHelperFactory;
import org.corenel.core.common.helper.ServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.common.pipe.ServicePipelineFactory;
import org.corenel.core.context.Context;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.services.batch.helper.DefaultBatchServiceHelper;
import org.corenel.services.disruptor.handler.chain.FirstDispatcherHandler;
import org.corenel.services.disruptor.handler.chain.FourthDispatcherHandler;
import org.corenel.services.disruptor.handler.chain.SecondDispatcherHandler1;
import org.corenel.services.disruptor.handler.chain.SecondDispatcherHandler2;
import org.corenel.services.disruptor.handler.chain.SingleDispatcherHandler;
import org.corenel.services.disruptor.handler.chain.ThirdDispatcherHandler1;
import org.corenel.services.disruptor.handler.chain.ThirdDispatcherHandler2;
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
public class HandlerChainDaemonServiceTest {
	
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
	public void singleChainByDaemonServiceTest() throws Exception{
		
		SingleDispatcherHandler<ServiceHelperHolder<ServiceHelper>> singleHandler = new SingleDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain.setCurrentEventHandlers(new EventHandler[]{ singleHandler }); // current

		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		batchServiceHelper.addEventHandlerChain(new EventHandlerChain[]{eventHandlerChain});
		
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		
		Pipeline<ServiceHelper> pipeline = ServicePipelineFactory.newPipeline();
		pipeline.attachServiceChain(batchServiceHelper);
		pipeline.attachServiceChain(ftpServiceHelper);
		pipeline.setServiceDispatcherType(ServiceDispatcherType.daemonService);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Pipeline<List<Object>>  result = producer.requestBody(ServiceExecutorType.Dispatcher.getRoute(), pipeline, Pipeline.class);
		
		logger.info(result.getResult().getMessage().toString());
		
	}
	
	/** 
	 * 이벤트 핸들러의 실행 순서가 다음과 같이 진행됩니다.
	 * 각 서비스는 해당 이벤트 핸들러를 설정한 정책에 의해 수행하게 되고 firstHandler 와 fourthHandler 는 단일 스레드로 secondHandler1, secondHandler2 및
	 * thirdHandler1, thirdHandler2 는 두개의 스레드로 동시에 수행하게 됩니다.  
	 * 
	 * 
	 ********************** batchService 의 수행 순서는 다음과 같습니다. **************************************
	 *
	 *                                              secondHandler1 -> thirdHandler1
	 *                                             /                               \
	 * Publisher -> Ring buffer ---> firstHandler -                                 -> fourthHandler 
	 *                                             \                               /
	 *                                              secondHandler2 -> thirdHandler2
	 *                                              
	 *                     
	 *                                               
	 *                                               
	 ********************** ftpService 의 수행순서는 다음과 같습니다. ******************************************
	 *
	 *                                              secondHandler1 
	 *                                             /                              
	 * Publisher -> Ring buffer ---> firstHandler -                                
	 *                                             \                             
	 *                                              secondHandler2
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void complexChainByDaemonServiceTest() throws Exception {
		
		FirstDispatcherHandler<ServiceHelperHolder<ServiceHelper>> firstHandler = new FirstDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		SecondDispatcherHandler1<ServiceHelperHolder<ServiceHelper>> secondHandler1 = new SecondDispatcherHandler1<ServiceHelperHolder<ServiceHelper>>();
		SecondDispatcherHandler2<ServiceHelperHolder<ServiceHelper>> secondHandler2 = new SecondDispatcherHandler2<ServiceHelperHolder<ServiceHelper>>();
		ThirdDispatcherHandler1<ServiceHelperHolder<ServiceHelper>> thirdHandler1 = new ThirdDispatcherHandler1<ServiceHelperHolder<ServiceHelper>>();
		ThirdDispatcherHandler2<ServiceHelperHolder<ServiceHelper>> thirdHandler2 = new ThirdDispatcherHandler2<ServiceHelperHolder<ServiceHelper>>();
		FourthDispatcherHandler<ServiceHelperHolder<ServiceHelper>> fourthHandler = new FourthDispatcherHandler<ServiceHelperHolder<ServiceHelper>>();
		
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain1 = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain1.setCurrentEventHandlers(new EventHandler[]{ firstHandler}); // current
		eventHandlerChain1.setNextEventHandlers(new EventHandler[]{ secondHandler1,secondHandler2 });// next

		// After 로 설정할 경우 반드시 이전에 참조되는 Currrent 핸들러가 지정되어 있어야 한다.
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain2 = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain2.setAfterEventHandlers(new EventHandler[]{ secondHandler1});// skip
		eventHandlerChain2.setNextEventHandlers(new EventHandler[]{ thirdHandler1}); // next

		// After 로 설정할 경우 반드시 이전에 참조되는 Currrent 핸들러가 지정되어 있어야 한다.
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain3 = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain3.setAfterEventHandlers(new EventHandler[]{secondHandler2});// skip
		eventHandlerChain3.setNextEventHandlers(new EventHandler[]{ thirdHandler2}); // next

		// After 로 설정할 경우 반드시 이전에 참조되는 Currrent 핸들러가 지정되어 있어야 한다.
		EventHandlerChain<ServiceHelperHolder<ServiceHelper>> eventHandlerChain4 = new EventHandlerChain<ServiceHelperHolder<ServiceHelper>>();
		eventHandlerChain4.setAfterEventHandlers(new EventHandler[]{thirdHandler1, thirdHandler2});// current
		eventHandlerChain4.setNextEventHandlers(new EventHandler[]{fourthHandler}); // skip
		
		DefaultBatchServiceHelper batchServiceHelper = serviceContext.getServiceHelperBean(DefaultBatchServiceHelper.class.getName(), DefaultBatchServiceHelper.class);
		// 수행하게 될 이벤트 핸들러를 설정한다, 단 설정하지 않을 시에는 단일스레드의 디폴트 핸들러가 수행하게 된다.
		batchServiceHelper.addEventHandlerChain(new EventHandlerChain[]{eventHandlerChain1, eventHandlerChain2,eventHandlerChain3,eventHandlerChain4});
		
		DefaultFtpServiceHelper ftpServiceHelper = serviceContext.getServiceHelperBean(DefaultFtpServiceHelper.class.getName(), DefaultFtpServiceHelper.class);
		// 수행하게 될 이벤트 핸들러를 설정한다, 단 설정하지 않을 시에는 단일스레드의 디폴트 핸들러가 수행하게 된다.
		ftpServiceHelper.addEventHandlerChain(new EventHandlerChain[]{eventHandlerChain1});
		
		Pipeline<ServiceHelper> pipeline = ServicePipelineFactory.newPipeline();
		pipeline.attachServiceChain(batchServiceHelper);
		pipeline.attachServiceChain(ftpServiceHelper);
		pipeline.setServiceDispatcherType(ServiceDispatcherType.daemonService);
		
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Pipeline<List<Object>> result = producer.requestBody(ServiceExecutorType.Dispatcher.getRoute(), pipeline, Pipeline.class);
		
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
