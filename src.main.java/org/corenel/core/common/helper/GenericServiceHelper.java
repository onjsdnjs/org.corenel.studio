package org.corenel.core.common.helper;

import org.corenel.core.context.Context;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial"})
public abstract class GenericServiceHelper implements ServiceHelper{
		
	private Context<String, Object> serviceContext;
	private EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] eventHandlerChain;
	
	public GenericServiceHelper(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
	}
	
	protected Context<String, Object> getServiceContext() {
		return serviceContext;
	}

	@Override
	public GenericServiceHelper clone() throws CloneNotSupportedException {
		return (GenericServiceHelper)super.clone();
	}

	@Override
	public void addEventHandlerChain(EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] handlerChain){
		eventHandlerChain = handlerChain;
	}

	@Override
	public EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] getEventHandlerChain() {
		return eventHandlerChain;
	}
	
	
}
