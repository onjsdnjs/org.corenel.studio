package org.corenel.core.reflect.executor;

import java.util.Map;

import org.corenel.core.reflect.ReflectExecutor;
import org.corenel.core.util.ClassUtil;

@SuppressWarnings("unchecked")
public class ClassReflectExecutor implements ReflectExecutor {

	private final Map<String, GetSetMaker> methods;

	private Class<?> clazz;

	public ClassReflectExecutor(Class<?> clazz, Map<String, GetSetMaker> methods) {
		this.methods = methods;
		this.clazz = clazz;
	}

	public <T> void set(Object obj, String fieldName, T value) {

		GetSetMaker getSetMaker = methods.get(fieldName);

		if (getSetMaker == null)
			throw new NoGetSetMakerFoundException(fieldName);

		try {

			getSetMaker.setter.invoke(obj, new Object[] { value });

		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				throw new ClassReflectExecutorException(e);
			}
		}
	}

	public <T> T get(Object obj, String fieldName) {

		GetSetMaker getSetMaker = methods.get(fieldName);
		if (getSetMaker == null) {
			throw new NoGetSetMakerFoundException(fieldName);
		}

		try {
			return (T) getSetMaker.getter.invoke(obj, (Object[]) null);
		} catch (Exception e) {
			throw new ClassReflectExecutorException(e);
		}
	}

	public <T> T createInstance(Object[] objects) {
		return ClassUtil.<T> createInstance(clazz);
	}
}
