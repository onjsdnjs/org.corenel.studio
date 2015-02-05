package org.corenel.core.common.pipe;

import java.util.Queue;

import org.corenel.core.common.domain.Response;
import org.corenel.core.common.helper.ServiceHelper;

public interface Pipeline {

	void attachServiceHelperChain(ServiceHelper serviceHelper);
	
	ServiceHelper dettachServiceHelperChain();
	
	Queue<ServiceHelper> getServiceQueue();

	void setResult(Response response);

	Response getResult();

	
}
