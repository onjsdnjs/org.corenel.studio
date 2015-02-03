package org.corenel.services.websocket.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.websocket.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractWebSocketServiceHelper extends GenericServiceHelper implements WebSocketServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private WebSocketService webSocketService;
	
	public AbstractWebSocketServiceHelper(Context<String, Object> context) {
		
		super(context);
		webSocketService = getContext().getBean(ApplicationConstants.WEBSOCKET_SERVICE, WebSocketService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
