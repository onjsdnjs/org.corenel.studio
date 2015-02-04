package org.corenel.core.disruptor.translator;

import org.corenel.core.message.CommandMessage;

import com.lmax.disruptor.EventTranslatorTwoArg;

public class DefultEventTranslatorTwoArg<T,A,B> implements EventTranslatorTwoArg<T,A,B> {
	
	@Override
	public void translateTo(T messageEvent, long sequence, A message1, B message2) {
		System.out.println(((CommandMessage)message1));
		System.out.println(((CommandMessage)message2));
	}
}
