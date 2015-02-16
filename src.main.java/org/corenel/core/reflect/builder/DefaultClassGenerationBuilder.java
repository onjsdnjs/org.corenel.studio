package org.corenel.core.reflect.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.corenel.core.reflect.ClassGenerationExecutor;
import org.corenel.core.reflect.executor.DefaultClassGenerationExecutor;
import org.corenel.core.reflect.executor.ClassMembers;
import org.corenel.core.util.ClassUtil;
import org.corenel.core.util.StringUtil;

public class DefaultClassGenerationBuilder implements ClassGenerationBuilder {

	public ClassGenerationExecutor buildClassGenerationExecutor(Class<?> klass) {
		
		Map<String, ClassMembers> methodRepository = new HashMap<String, ClassMembers>();
		List<Field> fields = ClassUtil.getFields(klass);

		for (Field field : fields) {

			String fieldName = field.getName();
			String methodName = StringUtil.getMethod(fieldName);

			try {

				generateClassMembers(methodRepository, methodName, field, fieldName);

			} catch (NoSuchMethodException e) {

				methodName = StringUtil.transformCharacter(field.getName(), true);
				try {
					generateClassMembers(methodRepository, methodName, field, fieldName);
				} catch (NoSuchMethodException e1) {
				}
			}
		}

		return new DefaultClassGenerationExecutor(klass, methodRepository);
	}

	private void generateClassMembers(Map<String, ClassMembers> methodRepository, String method, Field field, String fieldName) throws NoSuchMethodException {

		ClassMembers generator = methodRepository.get(fieldName);
		if(generator != null) throw new RuntimeException(" already exist");
		
		Method getMethod = getMethod(field, method);
		Method setMethod = setMethod(field, method);
		ClassMembers classMember = new ClassMembers();
		classMember.setSetMethod(setMethod);
		classMember.setGetMethod(getMethod);
		
		methodRepository.put(fieldName, classMember);
	}

	private static Method setMethod(Field field, String method) throws NoSuchMethodException {
		Method setMethod = field.getDeclaringClass().getDeclaredMethod("set" + method, field.getType());
		return setMethod;
	}

	private static Method getMethod(Field field, String method) throws NoSuchMethodException {
		Method getMethod;
		try {
			getMethod = field.getDeclaringClass().getDeclaredMethod("get" + method, (Class[]) null);
		} catch (NoSuchMethodException e) {
			getMethod = field.getDeclaringClass().getDeclaredMethod("is" + method, (Class[]) null);
		}
		return getMethod;
	}
}
