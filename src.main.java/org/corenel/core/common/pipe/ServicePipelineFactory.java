package org.corenel.core.common.pipe;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.corenel.core.common.helper.ServiceHelper;
import org.springframework.stereotype.Component;

@Component("servicePipelineFactory")
public class ServicePipelineFactory{
	
	public static ServicePipeline newPipeline(){
		return new ServicePipeline();
	}
	
	static class ServicePipeline implements Pipeline {

		static Queue<ServiceHelper> serviceQueue = new LinkedBlockingQueue<ServiceHelper>();
		
		@Override
		public void addServiceHelperChain(ServiceHelper serviceHelper) {
			serviceQueue.offer(serviceHelper);
		}
		
		@Override
		public ServiceHelper removeServiceHelperChain() {
			return serviceQueue.poll();
		}
		
		@Override
		public Queue<ServiceHelper> getServiceQueue() {
			return serviceQueue;
		}
	}
}
