package org.corenel.core.message;

import java.util.Map;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public class MapMessage  extends CommandMessage{

	private Map<String, Object> map;
	
	public void putMessage(String key, Object value){
		map.put(key, value);
	}
	
	public Object getMessage(String key){
		return map.get(key);
	}
	
	@Override
	public String toString() {
		return "MapMessage";
	}
}
