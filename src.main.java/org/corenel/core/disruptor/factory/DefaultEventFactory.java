package org.corenel.core.disruptor.factory;

import com.lmax.disruptor.EventFactory;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("unchecked")
public class DefaultEventFactory<T> implements EventFactory<T>{
	
	private Class<?> clazz;
	
	public DefaultEventFactory(Class<?> clz) {
		clazz = clz;
	}

	@Override
	public T newInstance() {

		try {
			return (T) clazz.newInstance();
			
		} catch (InstantiationException e){} 
		  catch (IllegalAccessException e){}
		  return null;
	}
}
