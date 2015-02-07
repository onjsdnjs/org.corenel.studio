package org.corenel.services;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.camel.CamelContext;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.context.Context;
import org.corenel.core.context.boot.ComponentInitializer;
import org.corenel.services.batch.service.BatchService;
import org.corenel.services.excel.service.ExcelService;
import org.corenel.services.file.service.FileService;
import org.corenel.services.ftp.service.FtpService;
import org.corenel.services.jms.service.JmsService;
import org.corenel.services.mail.service.MailService;
import org.corenel.services.messaging.service.MessageService;
import org.corenel.services.monitor.service.MonitorService;
import org.corenel.services.push.service.PushService;
import org.corenel.services.tcp.service.TcpService;
import org.corenel.services.transcoder.service.TransCoderService;
import org.corenel.services.udp.service.UdpService;
import org.corenel.services.websocket.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class ServiceContextInitializer implements Serializable, ComponentInitializer{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Resource(name = "excelService")
    private ExcelService excelService;
    
    @Resource(name = "messageService")
    private MessageService messageService;
    
    @Resource(name = "batchService")
    private BatchService batchService;

    @Resource(name = "ftpService")
    private FtpService ftpService;

    @Resource(name = "fileService")
    private FileService fileService;
    
    @Resource(name = "jmsService")
    private JmsService jmsService;
    
    @Resource(name = "mailService")
    private MailService mailService;
    
    @Resource(name = "monitorService")
    private MonitorService monitorService;
    
    @Resource(name = "pushService")
    private PushService pushService;

    @Resource(name = "transCoderService")
    private TransCoderService transCoderService;
    
    @Resource(name = "tcpService")
    private TcpService tcpService;
    
    @Resource(name = "udpService")
    private UdpService udpService;
    
    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;
    
    @Resource(name = "serviceContext")
    private Context<String, Object> serviceContext;
    
    @Resource(name = "camelContext")
    private CamelContext camelContext;
	
    /** Excel Service */
	private static final String DefaultExcelUpLoadServiceHelper = "org.corenel.services.excel.helper.impl.upload.DefaultExcelUpLoadServiceHelper";
	private static final String DefaultExcelDownLoadServiceHelper = "org.corenel.services.excel.helper.impl.download.DefaultExcelDownLoadServiceHelper";
	
	/** Message Service */
	private static final String DefaultMessageServiceHelper = "org.corenel.services.messaging.helper.DefaultMessageServiceHelper";
	
	/** Batch Service */
	private static final String DefaultBatchServiceHelper = "org.corenel.services.batch.helper.DefaultBatchServiceHelper";
	
	/** Ftp Service */
	private static final String DefaultFtpServiceHelper = "org.corenel.services.ftp.helper.DefaultFtpServiceHelper";

	/** File Service */
	private static final String DefaultFileServiceHelper = "org.corenel.services.file.helper.DefaultFileServiceHelper";
	
	/** Jms Service */
	private static final String DefaultJmsServiceHelper = "org.corenel.services.jms.helper.DefaultJmsServiceHelper";
	
	/** Mail Service */
	private static final String DefaultMailServiceHelper = "org.corenel.services.mail.helper.DefaultMailServiceHelper";
	
	/** Monitor Service */
	private static final String DefaultMonitorServiceHelper = "org.corenel.services.monitor.helper.DefaultMonitorServiceHelper";
	
	/** Push Service */
	private static final String DefaultPushServiceHelper = "org.corenel.services.push.helper.DefaultPushServiceHelper";
	
	/** TransCoder Service */
	private static final String DefaultTransCoderServiceHelper = "org.corenel.services.transcoder.helper.DefaultTransCoderServiceHelper";
	
	/** Tcp Service */
	private static final String DefaultTcpServiceHelper = "org.corenel.services.tcp.helper.DefaultTcpServiceHelper";
	
	/** Udp Service */
	private static final String DefaultUdpServiceHelper = "org.corenel.services.udp.helper.DefaultUdpServiceHelper";
	
	/** WebSocket Service */
	private static final String DefaultWebSocketServiceHelper = "org.corenel.services.websocket.helper.DefaultWebSocketServiceHelper";
	
	/** Dispatcher Service */
	private static final String serviceDispatcher = "org.corenel.core.disruptor.helper.DefaultDisruptorServiceHelper";
	
	private static List<String> services = new ArrayList<String>();
	static{
		
		services.add(DefaultExcelUpLoadServiceHelper);
		services.add(DefaultExcelDownLoadServiceHelper);
		services.add(DefaultMessageServiceHelper);
		services.add(DefaultBatchServiceHelper);
		services.add(DefaultFtpServiceHelper);
		services.add(DefaultFileServiceHelper);
		services.add(DefaultJmsServiceHelper);
		services.add(DefaultMailServiceHelper);
		services.add(DefaultMonitorServiceHelper);
		services.add(DefaultPushServiceHelper);
		services.add(DefaultTransCoderServiceHelper);
		services.add(DefaultTcpServiceHelper);
		services.add(DefaultUdpServiceHelper);
		services.add(DefaultWebSocketServiceHelper);
		
	}
	
	public static String getServiceDispatcher(){
		return serviceDispatcher;
	}

	@Override
	public void initialize() throws Exception {
		
		logger.info(">> ServiceInitializer initializing()...");
		toString();
		
		Map<String, Object> beanMap = new HashMap<String, Object>();
		
		beanMap.put(ApplicationConstants.EXCEL_SERVICE, excelService);
		beanMap.put(ApplicationConstants.MESSAGE_SERVICE, messageService);
		beanMap.put(ApplicationConstants.BATCH_SERVICE, batchService);
		beanMap.put(ApplicationConstants.FTP_SERVICE, ftpService);
		beanMap.put(ApplicationConstants.FILE_SERVICE, fileService);
		beanMap.put(ApplicationConstants.MAIL_SERVICE, mailService);
		beanMap.put(ApplicationConstants.MONITOR_SERVICE, monitorService);
		beanMap.put(ApplicationConstants.PUSH_SERVICE, pushService);
		beanMap.put(ApplicationConstants.TRANSCODER_SERVICE, transCoderService);
		beanMap.put(ApplicationConstants.TCP_SERVICE, tcpService);
		beanMap.put(ApplicationConstants.UDP_SERVICE, udpService);
		beanMap.put(ApplicationConstants.JMS_SERVICE, jmsService);
		beanMap.put(ApplicationConstants.WEBSOCKET_SERVICE, webSocketService);
		beanMap.put(ApplicationConstants.SERVICE_CLASSES, services); 
		beanMap.put(ApplicationConstants.SERVICE_DISPATCHER, serviceDispatcher); 
		
    	serviceContext.initializingBean(beanMap);
	}

	@Override
	public String toString() {
		return "ServiceInitializer [excelService=" + excelService
				+ ", messageService=" + messageService + ", batchService="
				+ batchService + ", ftpService=" + ftpService
				+ ", fileService=" + fileService + ", jmsService=" + jmsService
				+ ", mailService=" + mailService + ", monitorService="
				+ monitorService + ", pushService=" + pushService
				+ ", transCoderService=" + transCoderService + ", tcpService="
				+ tcpService + ", udpService=" + udpService
				+ ", webSocketService=" + webSocketService + "]";
	}
	
	

}
