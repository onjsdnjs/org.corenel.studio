package org.corenel.core.common.pipe;

import java.util.Queue;
import org.corenel.core.common.domain.Response;
import org.corenel.core.common.helper.ServiceHelper;

/**
 * @author Á¤¼ö¿ø
 */
public interface Pipeline {

	void attachServiceHelperChain(ServiceHelper serviceHelper);
	
	ServiceHelper dettachServiceHelperChain();
	
	Queue<ServiceHelper> getServiceQueue();
	
	void setServiceList(ServiceHelper[] serviceList) ;

	ServiceHelper[] getServiceList();

	void setResult(Response response);

	Response getResult();

	boolean isInterWorking();

	void setInterWorking(boolean interWorking);
}
