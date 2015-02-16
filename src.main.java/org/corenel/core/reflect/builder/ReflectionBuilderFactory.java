package org.corenel.core.reflect.builder;

public class ReflectionBuilderFactory {
	
	public static ReflectionBuilder getReflectionBuilder() {
    	return AbstractClassReflectionBuilder.getInstance();
	}
}
