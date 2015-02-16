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

public class ClassReflectionBuilder implements ClassGenerationBuilder {

	public ClassGenerationExecutor buildClassGenerationExecutor(Class<?> clazz) {
		
		Map<String, ClassMembers> methodRepository = new HashMap<String, ClassMembers>();
		List<Field> fields = ClassUtil.getFields(clazz);

		for (Field field : fields) {

			String fieldName = field.getName();
			String methodName = StringUtil.getMethodName(fieldName);

			try {

				settingsClassProperties(methodRepository, methodName, field, fieldName);

			} catch (NoSuchMethodException e) {

				methodName = StringUtil.changeFirstCharacter(field.getName(), true);
				try {
					settingsClassProperties(methodRepository, methodName, field, fieldName);
				} catch (NoSuchMethodException e1) {
				}
			}
		}

		return new DefaultClassGenerationExecutor(clazz, methodRepository);
	}

	private void settingsClassProperties(Map<String, ClassMembers> methodRepository, String methodName, Field field, String fieldName) throws NoSuchMethodException {

		ClassMembers generator = methodRepository.get(fieldName);
		if(generator != null) throw new RuntimeException(" already exist");
		
		Method getMethod = getMethod(field, methodName);
		Method setMethod = setMethod(field, methodName);
		ClassMembers classProperties = new ClassMembers();
		classProperties.setSetMethod(setMethod);
		classProperties.setGetMethod(getMethod);
		
		methodRepository.put(fieldName, classProperties);
	}

	private static Method setMethod(Field field, String methodName) throws NoSuchMethodException {
		Method setMethod = field.getDeclaringClass().getDeclaredMethod("set" + methodName, field.getType());
		return setMethod;
	}

	private static Method getMethod(Field field, String methodName) throws NoSuchMethodException {
		Method getMethod;
		try {
			getMethod = field.getDeclaringClass().getDeclaredMethod("get" + methodName, (Class[]) null);
		} catch (NoSuchMethodException e) {
			getMethod = field.getDeclaringClass().getDeclaredMethod("is" + methodName, (Class[]) null);
		}
		return getMethod;
	}
}
