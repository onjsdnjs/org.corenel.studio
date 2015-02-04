package org.corenel.core.common;

import java.util.Map;

@SuppressWarnings("serial")
public class ServiceExceptionResolver extends RuntimeException{
	
	private String msg;
	private Map<String, Object> map;
	
	public ServiceExceptionResolver() {
		super();
	}

	public ServiceExceptionResolver(Map<String, Object> map) {
		this.map = map;
	}

	public ServiceExceptionResolver(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceExceptionResolver(String message) {
		super(message);
	}

	public ServiceExceptionResolver(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	
	public void setDetailMessage(String msg){
		this.msg = msg;
	}
	
	public String getDetailMessage(){
		return msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
