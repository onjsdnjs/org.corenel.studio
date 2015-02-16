package org.corenel.core.reflect.builder;

import org.corenel.core.reflect.ClassGenerationExecutor;

public interface ClassGenerationBuilder {

	ClassGenerationExecutor buildClassGenerationExecutor(Class<?> klass);

}
