package org.corenel.core.message;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public class ObjectMessage  extends CommandMessage{
	
	private Object object;

	public Object getObject(){
		return object;
	}

	public void setObject(Object object){
		this.object = object;
	}
	
	@Override
	public String toString() {
		return "ObjectMessage";
	}
}
