package org.corenel.core.message;

/**
 * @author Á¤¼ö¿ø
 */
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
