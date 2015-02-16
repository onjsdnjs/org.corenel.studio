package org.corenel.core.reflect.executor;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.corenel.core.reflect.ClassGenerationExecutor;
import org.corenel.core.util.ClassUtil;

@SuppressWarnings("unchecked")
public class DefaultClassGenerationExecutor implements ClassGenerationExecutor {

	private final Map<String, ClassMembers> methodRepository;

	private Class<?> clazz;

	public DefaultClassGenerationExecutor(Class<?> clazz, Map<String, ClassMembers> methodRepository) {
		this.methodRepository = methodRepository;
		this.clazz = clazz;
	}
	
	public <T> T newInstance(Object[] objects) {
		return ClassUtil.<T> createInstance(clazz);
	}

	public <T> void setInvoke(Object obj, String fieldName, T value) {

		ClassMembers generator = methodRepository.get(fieldName);
		Validate.notNull(generator);
		try {
			Object[] args = new Object[] { value };
			generator.getSetMethod().invoke(obj, args);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T getInvoke(Object obj, String fieldName) {

		ClassMembers generator = methodRepository.get(fieldName);
		Validate.notNull(generator);
		try {
			return (T) generator.getGetMethod().invoke(obj, (Object[]) null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
