package org.corenel.core.common.helper;

import java.io.Serializable;

import org.corenel.core.disruptor.handler.chain.EventHandlerChain;

/**
 * @author Á¤¼ö¿ø
 */
public interface ServiceHelper extends Serializable, Cloneable{
	
	void handleService() throws Exception;
	
	void executeService() throws Exception;
	
	void addEventHandlerChain(EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] handlerChain);
	
	EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] getEventHandlerChain();

}
