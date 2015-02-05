package org.corenel.services.messaging.helper;

import org.corenel.core.context.Context;
import org.corenel.core.message.CommandMessage;

/**

 * @author Á¤¼ö¿ø
 */

@SuppressWarnings("serial")
public class DefaultMessageServiceHelper extends AbstractMessageServiceHelper{
	
	public DefaultMessageServiceHelper(Context<String, Object> context) {
		super(context);
	}
	
	@Override
	public void sendMessage(CommandMessage sendMessageVO) throws Exception{
    	executeService();
	}
	
	@Override
	public DefaultMessageServiceHelper clone() throws CloneNotSupportedException {
		return (DefaultMessageServiceHelper)super.clone();
	}
}
