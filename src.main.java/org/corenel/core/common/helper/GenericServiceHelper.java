package org.corenel.core.common.helper;

import org.corenel.core.context.Context;
import org.corenel.core.reflect.builder.ReflectionBuilder;
import org.corenel.core.reflect.builder.ReflectionBuilderFactory;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial"})
public abstract class GenericServiceHelper implements ServiceHelper{
		
	private ReflectionBuilder reflectionBuilder;
	private Context<String, Object> serviceContext;
	
	public GenericServiceHelper(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
		reflectionBuilder = ReflectionBuilderFactory.getReflectionBuilder();
	}
	
	protected Context<String, Object> getServiceContext() {
		return serviceContext;
	}

	protected ReflectionBuilder getReflectionBuilder() {
		return reflectionBuilder;
	}
	
	@Override
	public GenericServiceHelper clone() throws CloneNotSupportedException {
		return (GenericServiceHelper)super.clone();
	}
	
}
