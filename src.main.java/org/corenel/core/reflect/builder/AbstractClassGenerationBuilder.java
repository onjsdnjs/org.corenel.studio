package org.corenel.core.reflect.builder;

public abstract class AbstractClassGenerationBuilder implements ClassGenerationBuilder {

	private static ClassGenerationBuilder classGenerationBuilder;

	public static ClassGenerationBuilder getInstance() {
		
		synchronized(AbstractClassGenerationBuilder.class){
			if (classGenerationBuilder == null)
				classGenerationBuilder = new DefaultClassGenerationBuilder();
				return classGenerationBuilder;
			}
	}
}
