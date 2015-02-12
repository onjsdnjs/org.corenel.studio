package org.corenel.core.common.factory;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.context.Context;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractServiceHelperFactory implements ServiceHelperFactory {
	
	private Class<?>[] paramTypes = {Context.class};
	private String[] keys = {ApplicationConstants.SERVICE_CONTEXT};
	private Object[] args;
	
	private Context<String, Object> serviceContext;

	public void setServiceContext(Context<String, Object> serviceContext) {
		this.serviceContext = serviceContext;
		args = this.serviceContext.getBeans(keys, paramTypes);
	}
	
	public Context<String, Object> getServiceContext() {
		return serviceContext;
	}

	public Object[] getArgs() {
		return args;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public String[] getKeys() {
		return keys;
	}
}
