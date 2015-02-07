package org.corenel.core.disruptor.manager;

public interface DisruptorLifecycle<T> {

	void start();
	
	void controlledShutdown();
	
	void halt();
	
	void awaitAndShutdown(long time);
	
}
