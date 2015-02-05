package org.corenel.core.message;


@SuppressWarnings("serial")
public class TextMessage  extends CommandMessage{
	
	private String text;

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}
	
	@Override
	public String toString() {
		return "TextMessage";
	}
}