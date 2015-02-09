package org.corenel.core.common.pipe;

import java.util.Queue;

import org.corenel.core.common.domain.Response;
import org.corenel.core.common.domain.ServiceType.ServiceDispatcherType;
import org.corenel.core.common.helper.ServiceHelper;

/**
 * @author Á¤¼ö¿ø
 */
public interface Pipeline<T> {

	void attachServiceChain(T t);
	
	T detachServiceChain();
	
	Queue<T> getServiceQueue();
	
	void setServiceList(ServiceHelper[] serviceList) ;

	ServiceHelper[] getServiceList();

	void setResult(Response response);

	Response getResult();

	ServiceDispatcherType getServiceDispatcherType();

	void setServiceDispatcherType(ServiceDispatcherType type);
}
