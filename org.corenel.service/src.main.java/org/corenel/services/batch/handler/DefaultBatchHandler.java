package org.corenel.services.batch.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultBatchHandler extends AbstractHandler implements BatchHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultBatchHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultBatchHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
