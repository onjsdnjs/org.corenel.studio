package org.corenel.core.disruptor.executor;

import java.io.Serializable;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;

public interface DisruptorExecutor<T> extends Serializable {

	void publish(EventTranslator<T> eventTranslator);
	
	<A> void publish(EventTranslatorOneArg<T,A> eventTranslator, A arg);
	
	<A, B> void publish(EventTranslatorTwoArg<T,A,B> eventTranslator, A arg1, B arg2); 
	
	<A, B, C> void publish(EventTranslatorThreeArg<T,A,B,C> eventTranslator, A arg1, B arg2, C arg3); 
	
	void disruptorEventHandler();
	
	void disruptorExceptionHandler();

}
