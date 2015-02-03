package org.corenel.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.corenel.core.common.ServiceExceptionResolver;


@SuppressWarnings("unchecked")
public class ClassUtil {

	private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap<Class<?>, Class<?>>(8);

	static {
		primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
		primitiveWrapperTypeMap.put(Byte.class, byte.class);
		primitiveWrapperTypeMap.put(Character.class, char.class);
		primitiveWrapperTypeMap.put(Double.class, double.class);
		primitiveWrapperTypeMap.put(Float.class, float.class);
		primitiveWrapperTypeMap.put(Integer.class, int.class);
		primitiveWrapperTypeMap.put(Long.class, long.class);
		primitiveWrapperTypeMap.put(Short.class, short.class);
	}

	public static final Map<String, Class<?>> primitiveTypes = new HashMap<String, Class<?>>(8);

	static {
		primitiveTypes.put("int", int.class);
		primitiveTypes.put("double", double.class);
		primitiveTypes.put("float", float.class);
		primitiveTypes.put("long", long.class);
		primitiveTypes.put("short", short.class);
		primitiveTypes.put("boolean", boolean.class);
	}

	public static List<Field> getFields(Class<?> cls) {

		List<Field> fields = new ArrayList<Field>();

		Field[] fieldArr = cls.getDeclaredFields();

		for (Field field : fieldArr) {
			int modifier = field.getModifiers();

			if ((modifier & 0x08) == 0x08) // static
				continue;

			fields.add(field);
		}

		Class<?> superclass = cls.getSuperclass();
		if (superclass != null) {
			if (superclass == java.lang.Object.class)
				return fields;

			// Recursive call.
			List<Field> superFields = getFields(cls.getSuperclass());
			if (superFields != null)
				fields.addAll(superFields);
		}

		return fields;
	}

	public static <T> T createInstance(String clsName) {

		return (T) createInstance(clsName, null);
	}

	public static <T> T createInstance(Class<?> voCls) {

		return (T) createInstance(voCls, null);
	}

	public static <T> T createInstance(String clsName, Object[] objects) {
		return (T) createInstance(forName(clsName), objects);

	}

	public static <T> T createInstance(Class<?> cls, Object[] objects) {
		Class<?>[] classes = getParameterTypes(objects);

		return (T) createInstance(cls, objects, classes);
	}

	public static Class<?>[] getParameterTypes(Object[] objects) {
		Class<?>[] classes = null;
		if (objects != null) {
			classes = new Class<?>[objects.length];
			int i = 0;
			for (Object obj : objects) {
				classes[i++] = obj.getClass();
			}
		}
		return classes;
	}

	@SuppressWarnings("rawtypes")
	public static <T> T createInstance(Class cls, Object[] objects, Class<?>[] classes) {

		try {
			Constructor<?> declaredConstructor = cls.getDeclaredConstructor(classes);
			return (T) declaredConstructor.newInstance(objects);
		} catch (Exception e) {
			throw new ServiceExceptionResolver(e);
		}
	}

	public static Class<?> getParameterizedType(Object obj, String fieldName) {

		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);

			return getParameterizedType(field);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Class<?> getParameterizedType(List<Object> list) {
		if (list == null || list.size() == 0)
			return null;
		Object object = list.get(0);
		return object.getClass();
	}

	public static Class<?> getParameterizedType(Field field) {

		if (field == null)
			return null;

		Type genericType = field.getGenericType();

		if (genericType == null)
			return null;

		if (genericType instanceof ParameterizedType) {
			ParameterizedType wrappedType = (ParameterizedType) genericType;

			return (Class<?>) wrappedType.getActualTypeArguments()[0];
		}
		return null;
	}

	public static boolean isSimpleProperty(Class<?> clazz) {
		return isSimpleValueType(clazz) || (clazz.isArray() && isSimpleValueType(clazz.getComponentType()));
	}

	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return primitiveWrapperTypeMap.containsKey(clazz);
	}

	public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
	}

	public static boolean isSimpleValueType(Class<?> clazz) {
		return isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz)
				|| Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz)
				|| clazz.equals(URI.class) || clazz.equals(URL.class) || clazz.equals(Locale.class)
				|| clazz.equals(Class.class);
	}

	public static Class<?> forName(String classname) throws ServiceExceptionResolver {
		try {
			return Class.forName(classname);
		} catch (ClassNotFoundException e) {
			throw new ServiceExceptionResolver(e);
		}
	}

}
