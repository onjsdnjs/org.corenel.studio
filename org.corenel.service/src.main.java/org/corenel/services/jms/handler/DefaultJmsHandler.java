package org.corenel.services.jms.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultJmsHandler extends AbstractHandler implements JmsHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultJmsHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultJmsHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
