package org.corenel.core.disruptor.publisher;

import org.corenel.core.disruptor.executor.DisruptorExecutor;
import org.corenel.core.disruptor.translator.DefultEventTranslatorThreeArg;

import com.lmax.disruptor.EventTranslatorThreeArg;

@SuppressWarnings("hiding")
public class DefaultEventPublisherThreeArg<T,A,B,C> extends AbstractEventPublisher<T> implements EventPublisher<T> {
	
	public DefaultEventPublisherThreeArg(DisruptorExecutor<T> executor) {
		super(executor);
	}

	@Override
	public <A, B, C> void publish(A arg1, B arg2, C arg3) throws Exception{
		EventTranslatorThreeArg<T,A,B,C> eventTranslator = new DefultEventTranslatorThreeArg<T,A,B,C>();
		getDisruptorExecutor().publish(eventTranslator, arg1, arg2, arg3);
	}
}
