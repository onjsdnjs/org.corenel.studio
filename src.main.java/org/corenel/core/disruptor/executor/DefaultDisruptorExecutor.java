package org.corenel.core.disruptor.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;

@SuppressWarnings("serial")
public class DefaultDisruptorExecutor<T> extends AbstractDisruptorExecutor<T>{
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void publish(EventTranslator<T> eventTranslator){
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator);
	}
	
	@Override
	public <A> void publish(EventTranslatorOneArg<T, A> eventTranslator, A arg) {
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg);
	}

	@Override
	public <A, B> void publish(EventTranslatorTwoArg<T, A, B> eventTranslator, A arg1, B arg2) {
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg1, arg2);
	}

	@Override
	public <A, B, C> void publish( EventTranslatorThreeArg<T, A, B, C> eventTranslator, A arg1, B arg2, C arg3) {
		logger.info("Publish Event()..");
		getRingBuffer().publishEvent(eventTranslator, arg1, arg2, arg3);
	}
}
