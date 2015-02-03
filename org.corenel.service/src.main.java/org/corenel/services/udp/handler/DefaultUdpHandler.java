package org.corenel.services.udp.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultUdpHandler extends AbstractHandler implements UdpHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultUdpHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultUdpHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
