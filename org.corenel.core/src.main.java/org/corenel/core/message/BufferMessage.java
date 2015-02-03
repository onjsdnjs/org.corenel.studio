package org.corenel.core.message;



@SuppressWarnings("serial")
public class BufferMessage extends CommandMessage {
	
	private byte buffer;
	private byte[] buffers;
	
	public byte	readByte(){
		return buffer;
	}
     
	public byte[] readBytes(){
		return buffers;
	}
	
	public void writeBytes(byte value){
		buffer = value;
	}

	public void writeBytes(byte[] value){
		buffers = value;
	}

	public void writeBytes(byte[] value, int offset, int length){
		System.arraycopy(value, offset, buffers, 0, length);
	}

	@Override
	public String toString() {
		return "BufferMessage";
	}
}
