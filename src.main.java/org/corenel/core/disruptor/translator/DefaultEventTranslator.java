package org.corenel.core.disruptor.translator;

import com.lmax.disruptor.EventTranslator;

public class DefaultEventTranslator<T> implements EventTranslator<T> {
	
	@Override
	public void translateTo(T messageEvent, long sequence) {
		System.out.println(messageEvent);
	}
}
