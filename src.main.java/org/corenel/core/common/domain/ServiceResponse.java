package org.corenel.core.common.domain;

public class ServiceResponse implements Response {
	
	private Object object;

	@Override
	public void setMessage(Object obj) {
		object = obj;
	}

	@Override
	public Object getMessage() {
		return object;
	}

}
