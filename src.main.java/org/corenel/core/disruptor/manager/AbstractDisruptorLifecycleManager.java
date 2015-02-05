package org.corenel.core.disruptor.manager;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;

public abstract class AbstractDisruptorLifecycleManager<T> implements DisruptorLifecycle<T>{

	private Disruptor<T> disruptor;
	private ExecutorService executor;
	private String threadName;
	
	public abstract void init();
	
	@Override
	public void controlledShutdown() {
		
		disruptor.shutdown();
		executor.shutdown();
		
	}

	@Override
	public void halt() {
		
		executor.shutdownNow();
		disruptor.halt();
		
	}

	@Override
	public void awaitAndShutdown(long time) {
		
		
		try {
			disruptor.shutdown(time, TimeUnit.SECONDS);
			executor.shutdown();
		    if (!executor.awaitTermination(time, TimeUnit.SECONDS)) {
		        executor.shutdownNow();
		        if (!executor.awaitTermination(time, TimeUnit.SECONDS)) {
		        }
		    }
		} catch (InterruptedException e1) {
		    executor.shutdownNow();
		    Thread.currentThread().interrupt();
		}catch (TimeoutException e) {
		}
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	public String getThreadName() {
		return threadName;
	}
	
	public Disruptor<T> getDisruptor() {
		return disruptor;
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setDisruptor(Disruptor<T> disruptor) {
		this.disruptor = disruptor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

}
