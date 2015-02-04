package org.corenel.core.disruptor.publisher;

import org.corenel.core.disruptor.executor.DisruptorExecutor;

public abstract class AbstractEventPublisher<T> implements EventPublisher<T> {
	
	private DisruptorExecutor<T> disruptorExecutor;

	public AbstractEventPublisher(DisruptorExecutor<T> executor) {
		disruptorExecutor = executor;
	}

	protected DisruptorExecutor<T> getDisruptorExecutor() {
		return disruptorExecutor;
	}

	@Override
	public void publish() {}
	@Override
	public <A> void publish(A arg1) {}
	@Override
	public <A, B> void publish(A arg1, B arg2) {}
	@Override
	public <A, B, C> void publish(A arg1, B arg2, C arg3) {}
	
}
