package org.corenel.services.transcoder.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultTransCoderHandler extends AbstractHandler implements TransCoderHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultTransCoderHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultTransCoderHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
