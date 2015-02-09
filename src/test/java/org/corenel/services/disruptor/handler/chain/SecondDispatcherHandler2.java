package org.corenel.services.disruptor.handler.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.corenel.core.common.helper.ServiceHelperHolder;

import com.lmax.disruptor.EventHandler;

@SuppressWarnings("unchecked")
public class SecondDispatcherHandler2<T> implements EventHandler<T> {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
		
		logger.info("DispatcherHandler : " + ((ServiceHelperHolder<T>)event).getServiceHelper().getClass().getName() + " handleService()");
		
		((ServiceHelperHolder<T>)event).getServiceHelper().handleService();
	}

}
