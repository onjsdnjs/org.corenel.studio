package org.corenel.core.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.ServiceExceptionResolver;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.common.helper.ServiceHelper;

/**
 * @author Á¤¼ö¿ø
 */

@SuppressWarnings({ "unchecked", "serial" })
@Component("serviceContext")
public class ServiceContext<K,V> implements Context<K,V>{

	private Map<String,Object> beanMap;
	
	@Override
	public void initializingBean(Map<K,V> map) {
		
		beanMap = new HashMap<String,Object>();
		beanMap.put(ApplicationConstants.CONTEXT, this);
		Set<K> keySet = map.keySet();
		for (K key : keySet) {
			Object object = map.get(key);
			beanMap.put((String)key, object);
		}
	}

	@Override
	public <T> T getBean(K key, Class<T> clazz) {
		return (T) beanMap.get(key);
	}
	
	@Override
	public Object getBean(K key) {
		return beanMap.get(key);
	}
	
	@Override
	public <T> T removeBean(K key, Class<T> clazz) {
		return (T)beanMap.remove(key);
	}

	@Override
	public <T> T getBeans(K[] key, Class<?>[] clazzes) {
		Object[] args = new Object[key.length];
		for (int i = 0; i < key.length; i++) {
			Object bean = getBean(key[i], clazzes[i]);
			args[i] = bean;
		}
		return (T) args;
	}
	
	public Map<String, Object> getBeanMap(){
		return beanMap;
	}

	@Override
	public void putBean(K key, V value) {
		beanMap.put((String)key, value);
	}
	
	@Override
	public <T> T getServiceHelperBean(K serviceHelperClassName, Class<T> serviceHelperClassType) throws CloneNotSupportedException {
		return (T)((GenericServiceHelper)getBean(serviceHelperClassName, serviceHelperClassType)).clone();
	}

	@Override
	public <T> T removeServiceHelperBean(K serviceHelperClassName, Class<T> serviceHelperClassType) {
		return (T)removeBean(serviceHelperClassName, serviceHelperClassType);
	}

}
