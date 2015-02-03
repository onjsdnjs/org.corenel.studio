package org.corenel.core.message;


public class MessageEvent {

	private TextMessage textMessage;
    private BufferMessage bufferMessage;
    private MapMessage mapMessage;
    private ObjectMessage objectMessage;
	
    public BufferMessage getBufferMessage() {
		return bufferMessage;
	}
    
	public void setBufferMessage(BufferMessage bufferMessage) {
		this.bufferMessage = bufferMessage;
	}
	
	public MapMessage getMapMessage() {
		return mapMessage;
	}
	
	public void setMapMessage(MapMessage mapMessage) {
		this.mapMessage = mapMessage;
	}
	
	public ObjectMessage getObjectMessage() {
		return objectMessage;
	}
	
	public void setObjectMessage(ObjectMessage objectMessage) {
		this.objectMessage = objectMessage;
	}
	
	public void setTextMessage(TextMessage textMessage) {
		this.textMessage = textMessage;
	}
	
	public TextMessage getTextMessage() {
		return textMessage;
	}

	@Override
	public String toString() {
		
		return "MessageEvent [bufferMessage=" + bufferMessage
				+ ", textMessage=" + textMessage + ", mapMessage=" + mapMessage
				+ ", objectMessage=" + objectMessage + "]";
	}
	
	
}
