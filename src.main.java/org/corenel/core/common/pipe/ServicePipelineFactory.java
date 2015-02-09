package org.corenel.core.common.pipe;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.corenel.core.common.domain.Response;
import org.corenel.core.common.domain.ServiceType.ServiceDispatcherType;
import org.corenel.core.common.helper.ServiceHelper;
import org.springframework.stereotype.Component;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("rawtypes")
@Component("servicePipelineFactory")
public class ServicePipelineFactory{
	
	public static ServicePipeline newPipeline(){
		return new ServicePipeline();
	}
	
	static class ServicePipeline<T> implements Pipeline<T> {
		
		private Response response;
		private ServiceDispatcherType serviceDispatcherType = ServiceDispatcherType.requestService;
		
		Queue<T> serviceQueue = new LinkedBlockingQueue<T>();

		static ServiceHelper[] serviceList ;
		
		@Override
		public void attachServiceHelperChain(T t) {
			serviceQueue.offer(t);
		}
		
		@Override
		public T detachServiceHelperChain() {
			return serviceQueue.poll();
		}
		
		@Override
		public Queue<T> getServiceQueue() {
			return serviceQueue;
		}

		public void setServiceList(ServiceHelper[] serviceList) {
			ServicePipeline.serviceList = serviceList;
		}
		
		public ServiceHelper[] getServiceList() {
			return serviceList;
		}

		@Override
		public void setResult(Response response) {
			this.response = response;
		}

		@Override
		public Response getResult() {
			return response;
		}

		public ServiceDispatcherType getServiceDispatcherType() {
			return serviceDispatcherType;
		}

		public void setServiceDispatcherType(ServiceDispatcherType serviceDispatcherType) {
			this.serviceDispatcherType = serviceDispatcherType;
		}
	}
}
