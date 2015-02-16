package org.corenel.core.reflect;

import org.apache.commons.lang.Validate;
import org.corenel.core.reflect.builder.ClassGenerationBuilderFactory;

public class ClassGenerationExecutorFactory {

    private ClassGenerationExecutorFactory() { }

    public static ClassGenerationExecutor build( String clsName ) {

    	Validate.notEmpty(clsName);
	    try {
	
	        Class<?> clazz = Class.forName( clsName );
	        Validate.notNull(clazz);
	        return ClassGenerationBuilderFactory.getClassGenerationBuilder().buildClassGenerationExecutor( clazz );
	
	    } catch ( ClassNotFoundException e ) {
	
	        throw new RuntimeException( e );
	    }
    }
}
