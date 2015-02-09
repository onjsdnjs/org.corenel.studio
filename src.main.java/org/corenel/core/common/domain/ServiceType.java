package org.corenel.core.common.domain;

public class ServiceType{

	public enum ServiceDispatcherType {
	
		disruptorEventPublisher, routeProcessor, routeProcessorRecursive;
		
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