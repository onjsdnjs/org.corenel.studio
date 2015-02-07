package org.corenel.core.disruptor.executor;

import java.io.Serializable;

import org.corenel.core.disruptor.handler.chain.EventHandlerChain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;

public interface DisruptorExecutor<T> extends Serializable {

	void publish(EventTranslator<T> eventTranslator) throws Exception;
	
	<A> void publish(EventTranslatorOneArg<T,A> eventTranslator, A arg) throws Exception;
	
	<A, B> void publish(EventTranslatorTwoArg<T,A,B> eventTranslator, A arg1, B arg2) throws Exception; 
	
	<A, B, C> void publish(EventTranslatorThreeArg<T,A,B,C> eventTranslator, A arg1, B arg2, C arg3) throws Exception; 
	
	void disruptorEventHandlerChain();
	
	void disruptorExceptionHandler();
	
	void setEventHandlerChain(EventHandlerChain<T>[] handlerChain);

}
