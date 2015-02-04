package org.corenel.core.message;


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
