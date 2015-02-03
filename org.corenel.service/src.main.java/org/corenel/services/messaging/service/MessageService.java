package org.corenel.services.messaging.service;

import java.io.Serializable;
import java.util.List;

import org.corenel.core.message.CommandMessage;
import org.corenel.services.messaging.sentence.SendResultVO;

/**

 * @author Á¤¼ö¿ø
 */
public interface MessageService extends Serializable {
	
	public List<SendResultVO> sendMessage(CommandMessage message) throws Exception ;
}
