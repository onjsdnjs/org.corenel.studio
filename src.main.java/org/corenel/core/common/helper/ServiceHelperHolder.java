package org.corenel.core.common.helper;

/**
 * @author Á¤¼ö¿ø
 */
public class ServiceHelperHolder<T> {

	private ServiceHelper serviceHelper;
	private ServiceHelper[] serviceHelpers;

	public ServiceHelper getServiceHelper() {
		return serviceHelper;
	}

	public void setServiceHelper(T message) {
		this.serviceHelper = (ServiceHelper) message;
	}

	public ServiceHelper[] getServiceHelpers() {
		return serviceHelpers;
	}

	public void setServiceHelpers(ServiceHelper[] serviceHelpers) {
		this.serviceHelpers = serviceHelpers;
	}
}
