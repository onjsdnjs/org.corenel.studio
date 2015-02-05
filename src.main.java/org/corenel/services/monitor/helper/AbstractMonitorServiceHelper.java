package org.corenel.services.monitor.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.monitor.service.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractMonitorServiceHelper extends GenericServiceHelper implements MonitorServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private MonitorService monitoryService;
	
	public AbstractMonitorServiceHelper(Context<String, Object> context) {
		
		super(context);
		monitoryService = getServiceContext().getBean(ApplicationConstants.MONITOR_SERVICE, MonitorService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + " handleService()");
	}
	
}
