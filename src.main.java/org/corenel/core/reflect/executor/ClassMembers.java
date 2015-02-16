package org.corenel.core.reflect.executor;

import java.lang.reflect.Method;

public class ClassMembers {

	private Method setMethod;
	private Method getMethod;

	public Method getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}

	public Method getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}
	
	
}
