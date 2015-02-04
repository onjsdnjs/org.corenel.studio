package org.corenel.core.reflect.executor;

public class NoGetSetMakerFoundException extends RuntimeException {

	private static final long serialVersionUID = 1680952349722563523L;

	public NoGetSetMakerFoundException(String fieldName) {
		super(fieldName);
	}

}
