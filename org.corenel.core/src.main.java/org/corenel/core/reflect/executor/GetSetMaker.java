package org.corenel.core.reflect.executor;

import java.lang.reflect.Method;

public class GetSetMaker {

	Method setter;
	Method getter;

	public GetSetMaker(Method getter, Method setter) {
		this.setter = setter;
		this.getter = getter;
	}
}
