package org.corenel.core.disruptor.executor;

import org.corenel.core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;

@SuppressWarnings("serial")
public class DefaultDisruptorExecutor<T> extends AbstractDisruptorExecutor<T>{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public DefaultDisruptorExecutor(Context<String, Object> serviceContext) {
		super(serviceContext);
	}

	@Override
	public void publish(EventTranslator<T> eventTranslator) throws Exception{
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator);
	}
	
	@Override
	public <A> void publish(EventTranslatorOneArg<T, A> eventTranslator, A arg) throws Exception{
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg);
	}

	@Override
	public <A, B> void publish(EventTranslatorTwoArg<T, A, B> eventTranslator, A arg1, B arg2) throws Exception{
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg1, arg2);
	}

	@Override
	public <A, B, C> void publish( EventTranslatorThreeArg<T, A, B, C> eventTranslator, A arg1, B arg2, C arg3) throws Exception{
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg1, arg2, arg3);
	}
}
