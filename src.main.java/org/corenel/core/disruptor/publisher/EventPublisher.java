package org.corenel.core.disruptor.publisher;

public interface EventPublisher<T> {

	void publish();
	
	<A> void publish(A arg1);
	
	<A, B> void publish(A arg1, B arg2);
	
	<A, B, C> void publish(A arg1, B arg2, C arg3);
}