package org.corenel.core.camel.handler;

import org.apache.camel.Exchange;

public interface ServiceHandlerResolver {

	public abstract void resolve(Exchange exchange) throws Exception;
}
