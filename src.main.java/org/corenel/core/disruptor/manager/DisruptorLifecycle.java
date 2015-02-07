package org.corenel.core.disruptor.manager;

/**
 * @author Á¤¼ö¿ø
 */
public interface DisruptorLifecycle<T> {

	void start();
	
	void controlledShutdown();
	
	void halt();
	
	void awaitAndShutdown(long time);
	
}
