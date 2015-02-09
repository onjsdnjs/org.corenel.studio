package org.corenel.core.common.domain;

public class ServiceType{

	public enum ServiceDispatcherType {
	
		EventPublish, RouteProcess;
		
	}

	public enum ServiceExecutorType {
		
		Pipeline {
		    @Override
		    public String toString() {
		      return "direct:service:pipeline";
		    }
		  },
		  
		Dispatcher {
			  @Override
			  public String toString() {
				  return "direct:service:pipeline";
			  }
		  },
		
	}
}