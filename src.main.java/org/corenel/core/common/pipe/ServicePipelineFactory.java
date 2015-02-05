package org.corenel.core.common.pipe;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.poi.hssf.record.formula.functions.T;
import org.corenel.core.common.domain.Response;
import org.corenel.core.common.helper.ServiceHelper;
import org.springframework.stereotype.Component;

@Component("servicePipelineFactory")
public class ServicePipelineFactory{
	
	public static ServicePipeline newPipeline(){
		return new ServicePipeline();
	}
	
	static class ServicePipeline implements Pipeline {
		
		private Response response;
		
		static Queue<ServiceHelper> serviceQueue = new LinkedBlockingQueue<ServiceHelper>();
		
		@Override
		public void attachServiceHelperChain(ServiceHelper serviceHelper) {
			serviceQueue.offer(serviceHelper);
		}
		
		@Override
		public ServiceHelper dettachServiceHelperChain() {
			return serviceQueue.poll();
		}
		
		@Override
		public Queue<ServiceHelper> getServiceQueue() {
			return serviceQueue;
		}

		@Override
		public void setResult(Response response) {
			this.response = response;
		}

		@Override
		public Response getResult() {
			return response;
		}

	}
}
