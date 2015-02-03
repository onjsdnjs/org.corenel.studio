package org.corenel.services.messaging.helper;

import org.corenel.core.context.Context;
import org.corenel.core.message.CommandMessage;

/**

 * @author Á¤¼ö¿ø
 */

@SuppressWarnings("serial")
public class InterWorkingMessageServiceHelper extends DefaultMessageServiceHelper{
	
	public InterWorkingMessageServiceHelper(Context<String, Object> context) {
		super(context);
	}
	
	@Override
	public void handleService() throws Exception {
		sendMessage(getSendMessageVO());
	}
	
	@Override
	public void sendMessage(CommandMessage sendMessageVO) throws Exception{
    	executeService();
	}
}
