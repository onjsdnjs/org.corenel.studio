package org.corenel.core.reflect.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.corenel.core.reflect.FieldAlreadyExistException;
import org.corenel.core.reflect.ReflectExecutor;
import org.corenel.core.reflect.executor.ClassReflectExecutor;
import org.corenel.core.reflect.executor.GetSetMaker;
import org.corenel.core.util.ClassUtil;
import org.corenel.core.util.StringUtil;

public class ClassReflectionBuilder implements ReflectionBuilder {

	private static ReflectionBuilder reflectionBuilder;

	private ClassReflectionBuilder() {
	}

	public ReflectExecutor buildReflectExecutor(Class<?> clazz) {
		
		Map<String, GetSetMaker> methods = new HashMap<String, GetSetMaker>();
		List<Field> fields = ClassUtil.getFields(clazz);

		for (Field field : fields) {

			String fieldName = field.getName();
			String methodName = StringUtil.getMethodName(fieldName);

			try {

				extractMethodAndFieldInfo(methods, methodName, field, fieldName);

			} catch (NoSuchMethodException e) {

				methodName = StringUtil.changeFirstCharacter(field.getName(), true);

				try {

					extractMethodAndFieldInfo(methods, methodName, field, fieldName);

				} catch (NoSuchMethodException e2) {

				}
			}
		}

		return new ClassReflectExecutor(clazz, methods);
	}

	private void extractMethodAndFieldInfo(Map<String, GetSetMaker> methods, String methodName, Field field,
			String fieldName) throws NoSuchMethodException {

		GetSetMaker getterAndSetter = methods.get(fieldName);
		if (getterAndSetter != null)
			throw new FieldAlreadyExistException(fieldName);

		methods.put(fieldName, getSetSettings(field, methodName));
	}

	private static GetSetMaker getSetSettings(Field field, String methodName) throws NoSuchMethodException {

		Method getter = null;

		try {
			getter = field.getDeclaringClass().getDeclaredMethod("get" + methodName, (Class[]) null);
		} catch (NoSuchMethodException e) {
			getter = field.getDeclaringClass().getDeclaredMethod("is" + methodName, (Class[]) null);
		}
		Method setter = field.getDeclaringClass().getDeclaredMethod("set" + methodName, field.getType());

		return new GetSetMaker(getter, setter);
	}

	public static ReflectionBuilder getInstance() {
		if (reflectionBuilder == null)
			reflectionBuilder = new ClassReflectionBuilder();
		return reflectionBuilder;
	}
}
