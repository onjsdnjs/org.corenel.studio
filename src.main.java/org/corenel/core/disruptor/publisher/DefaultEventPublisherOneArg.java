package org.corenel.core.disruptor.publisher;

import org.corenel.core.disruptor.executor.DisruptorExecutor;
import org.corenel.core.disruptor.translator.DefaultEventTranslatorOneArg;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("hiding")
public class DefaultEventPublisherOneArg<T,A> extends AbstractEventPublisher<T> implements EventPublisher<T> {
	
	public DefaultEventPublisherOneArg(DisruptorExecutor<T> executor) {
		super(executor);
	}

	@Override
	public <A> void publish(A arg1) throws Exception{
		EventTranslatorOneArg<T,A> eventTranslator = new DefaultEventTranslatorOneArg<T,A>();
		getDisruptorExecutor().publish(eventTranslator, arg1);
	}
}
