package org.corenel.core.context;

import java.io.Serializable;
import java.util.Map;

import org.corenel.core.common.helper.ServiceHelper;

/**

 * @author Á¤¼ö¿ø
 */
public interface Context<K,V> extends Serializable{
	
	void putBean(K key, V value);

	<T> T getBean(K key, Class<T> clazz);

	Object getBean(K key);

	Map<String, Object> getBeanMap();

	<T> T getBeans(K[] key, Class<?>[] clazzes);

	public ServiceHelper getServiceBean(String serviceName, Class<?> classType) throws CloneNotSupportedException;
	
	public void initialize(Map<K,V> map);
}
