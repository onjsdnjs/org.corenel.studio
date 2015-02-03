package org.corenel.core.disruptor.handler.chain;

import com.lmax.disruptor.EventHandler;

public interface HandlerChain<T> {
	
	public EventHandler<T>[] getCurrentEventHandlers();
	
	public EventHandler<T>[] getNextEventHandlers();
	
	public EventHandler<T>[] getAfterEventHandlers();
	
}
