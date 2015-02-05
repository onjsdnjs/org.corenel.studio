package org.corenel.services.messaging.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.core.util.CommonPropertyService;
import org.corenel.services.messaging.helper.AbstractMessageServiceHelper;
import org.corenel.services.messaging.sentence.SendMessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class SendMessageHandler extends AbstractHandler implements MessageHandler{
	
	Logger logger =  LoggerFactory.getLogger(SendMessageHandler.class);
	private AbstractMessageServiceHelper serviceHelper;
	private CommonPropertyService propertiesService;
	
	public interface MessageSetup {
		public void setupCompleted(SendMessageVO sendMessageVO) throws Exception;
	}
	
	public SendMessageHandler(AbstractMessageServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
	}
	
}
