package org.corenel.services.jms.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.jms.service.JmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractJmsServiceHelper extends GenericServiceHelper implements WebJmsHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private JmsService jmsService;
	
	public AbstractJmsServiceHelper(Context<String, Object> context) {
		
		super(context);
		jmsService = getContext().getBean(ApplicationConstants.JMS_SERVICE, JmsService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
