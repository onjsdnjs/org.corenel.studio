package org.corenel.core.reflect;

public class FieldAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1544658430776167336L;

	public FieldAlreadyExistException(String fieldName) {
		super(fieldName);
	}

}
