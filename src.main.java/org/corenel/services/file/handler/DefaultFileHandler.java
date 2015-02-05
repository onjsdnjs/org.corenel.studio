package org.corenel.services.file.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultFileHandler extends AbstractHandler implements FileHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultFileHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultFileHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
