package org.corenel.core.common.domain;

public class ServiceType{

	public enum ServiceDispatcherType {
	
		requestService, daemonService;
		
	}

	public enum ServiceExecutorType {
		
		Boot ("direct:service:boot") ,
		Dispatcher("direct:service:dispatcher");
		
		private String route;

		private ServiceExecutorType(){}
		
		private ServiceExecutorType(String route) {
			this.route = route;
		}

		public String getRoute() {
			return route;
		}
	}
}