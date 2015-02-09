package org.corenel.core.common.domain;

public class ServiceType{

	public enum ServiceDispatcherType {
	
		requestService, daemonService;
		
	}

	public enum ServiceExecutorType {
		
		Dispatcher {
			  @Override
			  public String toString() {
				  return "direct:service:dispatcher";
			  }
		  }
		
	}
}