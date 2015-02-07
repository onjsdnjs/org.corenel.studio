package org.corenel.core.disruptor.executor;

import java.util.concurrent.Executor;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

/**
 * @author Á¤¼ö¿ø
 */
public class DisruptorWrapper<T> extends Disruptor<T> {

	public DisruptorWrapper(final EventFactory<T> eventFactory,
            final int ringBufferSize,
            final Executor executor,
            final ProducerType producerType,
            final WaitStrategy waitStrategy) {
		super(eventFactory, ringBufferSize, executor, producerType, waitStrategy);
	}

	@Override
	public EventHandlerGroup<T> handleEventsWith( EventHandler<? super T>... handlers) {
		return super.handleEventsWith(handlers);
	}

	@Override
	public EventHandlerGroup<T> after(EventHandler<T>... handlers) {
		return super.after(handlers);
	}
	
	

}
