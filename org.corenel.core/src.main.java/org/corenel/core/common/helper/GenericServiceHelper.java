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
	private Context<String, Object> context;
	
	public GenericServiceHelper(Context<String, Object> ctx) {
		context = ctx;
		reflectionBuilder = ReflectionBuilderFactory.getReflectionBuilder();
	}
	
	public Context<String, Object> getContext() {
		return context;
	}

	public ReflectionBuilder getReflectionBuilder() {
		return reflectionBuilder;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
