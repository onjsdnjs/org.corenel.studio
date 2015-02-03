package org.corenel.core.common.helper;

public class ServiceHelperHolder<T> {

	private ServiceHelper serviceHelper;

	public ServiceHelper getServiceHelper() {
		return serviceHelper;
	}

	public void setServiceHelper(T message) {
		this.serviceHelper = (ServiceHelper) message;
	}
	
}
