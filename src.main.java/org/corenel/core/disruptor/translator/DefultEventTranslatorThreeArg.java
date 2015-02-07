package org.corenel.core.disruptor.translator;

import org.corenel.core.message.CommandMessage;

import com.lmax.disruptor.EventTranslatorThreeArg;

/**
 * @author Á¤¼ö¿ø
 */
public class DefultEventTranslatorThreeArg<T, A, B, C> implements EventTranslatorThreeArg<T, A, B, C> {

	@Override
	public void translateTo(T messageEvent, long sequence, A message1, B message2, C message3) {
		System.out.println(((CommandMessage)message1));
		System.out.println(((CommandMessage)message2));
		System.out.println(((CommandMessage)message3));
	}

}
