package org.corenel.core.reflect.executor;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.corenel.core.reflect.ReflectExecutor;
import org.corenel.core.util.ClassUtil;

@SuppressWarnings("unchecked")
public class ClassReflectExecutor implements ReflectExecutor {

	private final Map<String, MethodMaker> methodRepository;

	private Class<?> clazz;

	public ClassReflectExecutor(Class<?> clazz, Map<String, MethodMaker> methodRepository) {
		this.methodRepository = methodRepository;
		this.clazz = clazz;
	}
	
	public <T> T newInstance(Object[] objects) {
		return ClassUtil.<T> createInstance(clazz);
	}

	public <T> void setInvoke(Object obj, String fieldName, T value) {

		MethodMaker methodMaker = methodRepository.get(fieldName);
		Validate.notNull(methodMaker);
		try {
			Object[] args = new Object[] { value };
			methodMaker.setMethod.invoke(obj, args);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T getInvoke(Object obj, String fieldName) {

		MethodMaker methodMaker = methodRepository.get(fieldName);
		Validate.notNull(methodMaker);
		try {
			return (T) methodMaker.getMethod.invoke(obj, (Object[]) null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
