package org.corenel.core.common.pipe;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.corenel.core.common.helper.ServiceHelper;

public class ServicePipeline implements Pipeline{

	BlockingQueue<ServiceHelper> serviceQueue = new LinkedBlockingQueue<ServiceHelper>();
	
	@Override
	public void attachServiceChain(ServiceHelper serviceHelper) {
		serviceQueue.offer(serviceHelper);
	}

	@Override
	public ServiceHelper dettachServiceChain() {
		return serviceQueue.poll();
	}

	public BlockingQueue<ServiceHelper> getServiceQueue() {
		return serviceQueue;
	}
	
}
