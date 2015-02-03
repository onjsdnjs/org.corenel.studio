package org.corenel.core.reflect.builder;

import org.corenel.core.reflect.ReflectExecutor;

public interface ReflectionBuilder {

	ReflectExecutor buildReflectExecutor(Class<?> cls);

}
