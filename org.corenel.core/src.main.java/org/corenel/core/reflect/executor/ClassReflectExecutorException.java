package org.corenel.core.reflect.executor;

public class ClassReflectExecutorException extends RuntimeException {

	private static final long serialVersionUID = -8117956850897282768L;

	public ClassReflectExecutorException(Exception e) {
		super(e);
	}

	public ClassReflectExecutorException(String message, Exception e) {
		super(message, e);
	}
}
