package org.corenel.services.ftp.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultFtpHandler extends AbstractHandler implements FtpHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultFtpHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultFtpHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
