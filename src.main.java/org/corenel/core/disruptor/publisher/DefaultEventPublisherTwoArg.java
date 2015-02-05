package org.corenel.core.disruptor.publisher;

import org.corenel.core.disruptor.executor.DisruptorExecutor;
import org.corenel.core.disruptor.translator.DefultEventTranslatorTwoArg;

import com.lmax.disruptor.EventTranslatorTwoArg;

@SuppressWarnings("hiding")
public class DefaultEventPublisherTwoArg<T,A,B> extends AbstractEventPublisher<T> implements EventPublisher<T> {
	
	public DefaultEventPublisherTwoArg(DisruptorExecutor<T> executor) {
		super(executor);
	}

	@Override
	public <A, B> void publish(A arg1, B arg2) throws Exception{
		EventTranslatorTwoArg<T,A,B> eventTranslator = new DefultEventTranslatorTwoArg<T,A,B>();
		getDisruptorExecutor().publish(eventTranslator, arg1, arg2);
	}
}
