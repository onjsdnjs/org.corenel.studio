package org.corenel.core.reflect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.corenel.core.reflect.builder.ReflectionBuilderFactory;

public class ReflectExecutorFactory {

    private static Map<Class<?>, ReflectExecutor> reflectors = new ConcurrentHashMap<Class<?>, ReflectExecutor>();

    private ReflectExecutorFactory() {

        throw new AssertionError( "Does not need to be instant" );
    }

    public static ReflectExecutor build( String clsName ) {
		if(clsName == null) {
			throw new RuntimeException("Class name must be not null.");
		}
    	   
	    try {
	
	        Class<?> clazz = Class.forName( clsName );
	        return build( clazz );
	
	    } catch ( ClassNotFoundException e ) {
	
	        throw new RuntimeException( e );
	    }
    }

    public static ReflectExecutor build( Class<?> clazz ) {
		if(clazz == null) {
			throw new RuntimeException("Class must be not null.");
		}
		
        ReflectExecutor reflector = reflectors.get( clazz );

        if ( reflector == null ) {
        	
            reflector = ReflectionBuilderFactory.getReflectionBuilder().buildReflectExecutor( clazz );

            reflectors.put( clazz, reflector );
        }

        return reflector;
    }
}
