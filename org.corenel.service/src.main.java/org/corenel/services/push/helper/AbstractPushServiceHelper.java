package org.corenel.services.push.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.push.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractPushServiceHelper extends GenericServiceHelper implements PushServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private PushService pushService;
	
	public AbstractPushServiceHelper(Context<String, Object> context) {
		
		super(context);
		pushService = getContext().getBean(ApplicationConstants.PUSH_SERVICE, PushService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
