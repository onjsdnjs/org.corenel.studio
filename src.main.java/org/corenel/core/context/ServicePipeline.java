package org.corenel.core.context;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.corenel.core.common.helper.GenericServiceHelper;

public class ServicePipeline implements Pipeline{

	BlockingQueue<Class<? extends GenericServiceHelper>> serviceQueue = new LinkedBlockingQueue<Class<? extends GenericServiceHelper>>();
	
	@Override
	public void attachServiceChain(Class<? extends GenericServiceHelper> serviceHelper) {
		serviceQueue.offer(serviceHelper);
	}

	@Override
	public void detachServiceChain(Class<? extends GenericServiceHelper> serviceHelper) {
		serviceQueue.poll();
	}
	
}
