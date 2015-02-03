package org.corenel.core.reflect;

public class UnSupportedFieldTypeExeception extends RuntimeException {

	private static final long serialVersionUID = -8695737085343458960L;

	public UnSupportedFieldTypeExeception(String name) {
		super(name);
	}

}
