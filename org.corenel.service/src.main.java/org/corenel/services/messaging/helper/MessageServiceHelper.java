package org.corenel.services.messaging.helper;

import org.corenel.core.message.CommandMessage;

/**

 * @author ������
 */
public interface MessageServiceHelper {
	
	public void initialize() throws Exception;
	public void sendMessage(CommandMessage sendMessageVO) throws Exception;
}
