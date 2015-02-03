package org.corenel.services.push.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultPushHandler extends AbstractHandler implements PushHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultPushHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultPushHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
