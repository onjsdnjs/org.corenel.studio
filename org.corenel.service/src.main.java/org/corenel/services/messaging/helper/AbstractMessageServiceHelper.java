package org.corenel.services.messaging.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.core.handler.Handler;
import org.corenel.services.messaging.handler.SendMessageHandler;
import org.corenel.services.messaging.sentence.SendMessageVO;
import org.corenel.services.messaging.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractMessageServiceHelper extends GenericServiceHelper implements MessageServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private MessageService messageService;
	private SendMessageVO sendMessageVO;
	
	public AbstractMessageServiceHelper(Context<String, Object> context) {
		
		super(context);
		messageService = getContext().getBean(ApplicationConstants.MESSAGE_SERVICE, MessageService.class);
		sendMessageVO = getContext().getBean(ApplicationConstants.EXCEL_MODEL_VO, SendMessageVO.class);
	}
	
	@Override
	public void executeService() throws Exception{
		getContext().putBean(ApplicationConstants.MESSAGE_RESULT_VO, getMessageService().sendMessage(getContext().getBean(ApplicationConstants.EXCEL_MODEL_VO, SendMessageVO.class)));
	}

	protected SendMessageVO getSendMessageVO() {
		return sendMessageVO;
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
		initialize();
		sendMessage(sendMessageVO);
	}
	
	@Override
	public void initialize() throws Exception {
		
		Handler handler = new SendMessageHandler(this);
		handler.handleRequest();
	}

	public MessageService getMessageService() {
		return messageService;
	}
}
