package org.corenel.core.reflect;


public interface ReflectExecutor {

	<T> T createInstance(Object[] parameters);
	
	<T> void set(Object obj, String fieldName, T value);

	<T> T get(Object obj, String fieldname);

}
