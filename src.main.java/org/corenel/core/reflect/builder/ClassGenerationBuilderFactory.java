package org.corenel.core.reflect.builder;

public class ClassGenerationBuilderFactory {
	
	public static ClassGenerationBuilder getClassGenerationBuilder() {
    	return AbstractClassGenerationBuilder.getInstance();
	}
}
