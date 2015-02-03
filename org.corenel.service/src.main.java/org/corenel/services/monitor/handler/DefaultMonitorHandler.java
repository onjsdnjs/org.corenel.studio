package org.corenel.services.monitor.handler;

import java.io.IOException;

import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.batch.helper.AbstractBatchServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
public class DefaultMonitorHandler extends AbstractHandler implements MonitorHandler{
	
	Logger logger =  LoggerFactory.getLogger(DefaultMonitorHandler.class);
	private AbstractBatchServiceHelper serviceHelper;
	
	public DefaultMonitorHandler(AbstractBatchServiceHelper helper) throws IOException {
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		
	}
}
