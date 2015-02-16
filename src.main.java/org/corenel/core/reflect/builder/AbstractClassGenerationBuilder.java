package org.corenel.core.reflect.builder;

public abstract class AbstractClassGenerationBuilder implements ClassGenerationBuilder {

	private static ClassGenerationBuilder reflectionBuilder;

	public static ClassGenerationBuilder getInstance() {
		
		synchronized(AbstractClassGenerationBuilder.class){
			if (reflectionBuilder == null)
				reflectionBuilder = new ClassReflectionBuilder();
				return reflectionBuilder;
			}
	}
}
