package org.corenel.core.reflect;


public interface ClassGenerationExecutor {

	<T> T newInstance(Object[] parameters);
	
	<T> void setInvoke(Object obj, String fieldName, T value);

	<T> T getInvoke(Object obj, String fieldname);

}
