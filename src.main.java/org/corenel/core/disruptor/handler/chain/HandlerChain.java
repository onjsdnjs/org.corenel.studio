package org.corenel.core.disruptor.handler.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author ������
 */
public interface HandlerChain<T> {
	
	EventHandler<T>[] getCurrentEventHandlers();
	
	EventHandler<T>[] getNextEventHandlers();
	
	EventHandler<T>[] getAfterEventHandlers();
	
}
