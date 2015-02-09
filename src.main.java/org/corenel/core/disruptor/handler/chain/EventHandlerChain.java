package org.corenel.core.disruptor.handler.chain;

import org.apache.commons.lang.ArrayUtils;

import com.lmax.disruptor.EventHandler;

/**
 * @author Á¤¼ö¿ø
 */
public class EventHandlerChain<T> implements HandlerChain<T> {

	private EventHandler<T>[] currentEventHandlers;
	private EventHandler<T>[] nextEventHandlers;
	private EventHandler<T>[] afterEventHandlers;
	
	public void setCurrentEventHandlers(EventHandler<T>[] currentEventHandlers) {
		this.currentEventHandlers = currentEventHandlers;
	}

	public void setNextEventHandlers(EventHandler<T>[] nextEventHandlers) {
		this.nextEventHandlers = nextEventHandlers;
	}

	public void setAfterEventHandlers(EventHandler<T>[] afterEventHandlers) {
		this.afterEventHandlers = afterEventHandlers;
	}

	public EventHandler<T>[] getCurrentEventHandlers() {
		return currentEventHandlers;
	}

	public EventHandler<T>[] getNextEventHandlers() {
		return nextEventHandlers;
	}

	public EventHandler<T>[] getAfterEventHandlers() {
		return afterEventHandlers;
	}

	public String printEventChainFlow() {
		StringBuilder str = new StringBuilder();
		
		if(! ArrayUtils.isEmpty(getCurrentEventHandlers())){
			printEventHandlers(str, getCurrentEventHandlers());
		}

		if(! ArrayUtils.isEmpty(getAfterEventHandlers())){
			printEventHandlers(str, getAfterEventHandlers());
		}
		
		if(! ArrayUtils.isEmpty(getNextEventHandlers())){
			str.append(" -> ");	
			printEventHandlers(str, getNextEventHandlers());
		}
		return str.toString();
	}

	private void printEventHandlers(StringBuilder str, EventHandler<T>[] eventHandlers) {
		str.append("{");
		for(int j=0;j<eventHandlers.length;j++){
			str.append(eventHandlers[j].getClass().getSimpleName());
			str.append(" | ");
		}
		str.delete(str.length()-3, str.length());
		str.append("}");
	}
}
