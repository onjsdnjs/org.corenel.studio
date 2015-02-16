package org.corenel.core.reflect;

import org.apache.commons.lang.Validate;
import org.corenel.core.reflect.builder.ReflectionBuilderFactory;

public class ReflectExecutorFactory {

    private ReflectExecutorFactory() { }

    public static ReflectExecutor build( String clsName ) {

    	Validate.notEmpty(clsName);
	    try {
	
	        Class<?> clazz = Class.forName( clsName );
	        Validate.notNull(clazz);
	        return ReflectionBuilderFactory.getReflectionBuilder().buildReflectExecutor( clazz );
	
	    } catch ( ClassNotFoundException e ) {
	
	        throw new RuntimeException( e );
	    }
    }
}
