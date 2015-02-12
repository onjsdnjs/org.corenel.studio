package org.corenel.core.common.factory;

import java.io.Serializable;

import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;

public interface ServiceHelperFactory extends Serializable{

	void initializeInstance(Context<String, Object> context) throws Exception;
	
	void createServiceHelper(Class<? extends GenericServiceHelper> clazz) throws Exception;
}
