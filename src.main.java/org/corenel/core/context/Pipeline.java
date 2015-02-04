package org.corenel.core.context;

import org.corenel.core.common.helper.GenericServiceHelper;

public interface Pipeline {

	void attachServiceChain(Class<? extends GenericServiceHelper> serviceHelper);
	
	void detachServiceChain(Class<? extends GenericServiceHelper> serviceHelper);
	
}
