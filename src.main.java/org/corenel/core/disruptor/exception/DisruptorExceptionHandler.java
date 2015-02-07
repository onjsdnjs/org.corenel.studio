package org.corenel.core.disruptor.exception;

import com.lmax.disruptor.ExceptionHandler;

/**
 * @author Á¤¼ö¿ø
 */
public class DisruptorExceptionHandler implements ExceptionHandler {
	
	protected String errorPrefix = "Ringbuffer Disruptor failed for thread: ";

	public DisruptorExceptionHandler(String threadName) {
		this.errorPrefix+= threadName + " | ";
	}

	@Override
	public void handleEventException(Throwable ex, long sequence, Object event) {
		StringBuilder str = new StringBuilder(errorPrefix);
		str.append("Sequence: ");
		str.append(sequence);
		str.append(" | ");
		str.append("Event: ");
		str.append(event);
		str.append(" | ");
		str.append("Exception message: ");
		str.append(ex.getMessage());
		
		throw new RuntimeException(str.toString(), ex);
	}

	@Override
	public void handleOnStartException(Throwable ex) {
	}

	@Override
	public void handleOnShutdownException(Throwable ex) {
	}
}
