package org.corenel.core.disruptor.publisher;

/**
 * @author Á¤¼ö¿ø
 */
public interface EventPublisher<T> {

	void publish() throws Exception;
	
	<A> void publish(A arg1) throws Exception;
	
	<A, B> void publish(A arg1, B arg2) throws Exception;
	
	<A, B, C> void publish(A arg1, B arg2, C arg3) throws Exception;
}