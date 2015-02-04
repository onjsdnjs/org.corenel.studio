package org.corenel.core.context;


import java.io.Serializable;
import java.util.Map;

/**

 * @author Á¤¼ö¿ø
 */
public interface Context<K,V> extends Serializable{
	
	void initializingBean(Map<K,V> map);

	void putBean(K key, V value);

	<T> T getBean(K key, Class<T> clazz);

	Object getBean(K key);
	
	<T> T removeBean(K key, Class<T> clazz);

	Map<String, Object> getBeanMap();

	<T> T getBeans(K[] key, Class<?>[] clazzes);

	<T> T getServiceHelperBean(K serviceHelperClassName, Class<T> serviceHelperClassType) throws CloneNotSupportedException;

	<T> T removeServiceHelperBean(K serviceHelperClassName, Class<T> serviceHelperClassType);
	
}
