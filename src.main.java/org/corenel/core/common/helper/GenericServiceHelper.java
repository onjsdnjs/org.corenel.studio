package org.corenel.core.common.helper;

import org.corenel.core.context.Context;
import org.corenel.core.disruptor.handler.chain.EventHandlerChain;
import org.corenel.core.reflect.builder.ClassGenerationBuilder;
import org.corenel.core.reflect.builder.ClassGenerationBuilderFactory;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial"})
public abstract class GenericServiceHelper implements ServiceHelper{
		
	private ClassGenerationBuilder reflectionBuilder;
	private Context<String, Object> serviceContext;
	private EventHandlerChain<ServiceHelperHolder<ServiceHelper>>[] eventHandlerChain;
	
	public GenericServiceHelper(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
		reflectionBuilder = ClassGenerationBuilderFactory.getClassGenerationBuilder();
	}
	
	protected Context<String, Object> getServiceContext() {
		return serviceContext;
	}

	protected ClassGenerationBuilder getReflectionBuilder() {
		return reflectionBuilder;
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
