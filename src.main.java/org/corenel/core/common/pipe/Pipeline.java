package org.corenel.core.common.pipe;

import java.util.Queue;

import org.corenel.core.common.helper.ServiceHelper;

public interface Pipeline {

	void addServiceHelperChain(ServiceHelper serviceHelper);
	
	ServiceHelper removeServiceHelperChain();
	
	Queue<ServiceHelper> getServiceQueue();
	
}
