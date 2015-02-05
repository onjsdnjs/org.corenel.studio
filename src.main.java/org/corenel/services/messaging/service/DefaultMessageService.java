package org.corenel.services.messaging.service;

import java.util.List;

import org.corenel.core.message.CommandMessage;
import org.corenel.services.messaging.sentence.SendResultVO;
import org.springframework.stereotype.Service;


@SuppressWarnings({"serial"})
@Service("messageService")
public class DefaultMessageService implements MessageService {
	
	@Override
	public List<SendResultVO> sendMessage(CommandMessage message) throws Exception {
		
		return null;
	}
}
