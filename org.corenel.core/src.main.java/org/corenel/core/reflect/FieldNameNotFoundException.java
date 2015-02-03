package org.corenel.core.reflect;

public class FieldNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5064659468289410036L;

	public FieldNameNotFoundException(String fieldName) {
		super(fieldName);
	}

}
