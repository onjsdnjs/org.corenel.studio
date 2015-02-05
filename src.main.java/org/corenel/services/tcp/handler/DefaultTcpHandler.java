package org.corenel.services.tcp.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultTcpHandler extends AbstractHandler implements TcpHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultTcpHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultTcpHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
