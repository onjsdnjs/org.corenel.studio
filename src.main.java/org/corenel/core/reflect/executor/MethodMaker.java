package org.corenel.core.reflect.executor;

import java.lang.reflect.Method;

public class MethodMaker {

	Method setMethod;
	Method getMethod;

	public MethodMaker(Method getMethod, Method setMethod) {
		this.setMethod = setMethod;
		this.getMethod = getMethod;
	}
}
