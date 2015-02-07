package org.corenel.core.disruptor.publisher;

import org.corenel.core.disruptor.executor.AbstractDisruptorExecutor;
import org.corenel.core.disruptor.translator.DefaultEventTranslator;

import com.lmax.disruptor.EventTranslator;

/**
 * @author Á¤¼ö¿ø
 */
public class DefaultEventPublisher<T> extends AbstractEventPublisher<T> implements EventPublisher<T> {
	
	public DefaultEventPublisher(AbstractDisruptorExecutor<T> executor) {
		super(executor);
	}

	@Override
	public void publish() throws Exception{
		EventTranslator<T> eventTranslator = new DefaultEventTranslator<T>();
		getDisruptorExecutor().publish(eventTranslator);
	}

}
