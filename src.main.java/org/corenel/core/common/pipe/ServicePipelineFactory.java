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
@Component("servicePipelineFactory")
public class ServicePipelineFactory{
	
	public static ServicePipeline newPipeline(){
		return new ServicePipeline();
	}
	
	static class ServicePipeline implements Pipeline {
		
		private Response response;
		private ServiceDispatcherType serviceDispatcherType = ServiceDispatcherType.requestService;
		
		static Queue<ServiceHelper> serviceQueue = new LinkedBlockingQueue<ServiceHelper>();

		static ServiceHelper[] serviceList ;
		
		@Override
		public void attachServiceHelperChain(ServiceHelper serviceHelper) {
			serviceQueue.offer(serviceHelper);
		}
		
		@Override
		public ServiceHelper detachServiceHelperChain() {
			return serviceQueue.poll();
		}
		
		@Override
		public Queue<ServiceHelper> getServiceQueue() {
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
