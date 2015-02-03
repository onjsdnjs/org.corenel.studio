package org.corenel.core.common.pipe;

import java.util.concurrent.BlockingQueue;

import org.corenel.core.common.helper.ServiceHelper;

public interface Pipeline {

	void attachServiceChain(ServiceHelper serviceHelper);
	
	ServiceHelper dettachServiceChain();
	
	BlockingQueue<ServiceHelper> getServiceQueue();
	
}
