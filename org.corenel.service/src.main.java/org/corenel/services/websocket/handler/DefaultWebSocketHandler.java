package org.corenel.services.websocket.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultWebSocketHandler extends AbstractHandler implements WebSocketHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultWebSocketHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultWebSocketHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
