package org.corenel.services.mail.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultMailHandler extends AbstractHandler implements MailHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultMailHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultMailHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
