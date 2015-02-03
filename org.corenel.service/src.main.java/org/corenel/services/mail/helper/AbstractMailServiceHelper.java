package org.corenel.services.mail.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractMailServiceHelper extends GenericServiceHelper implements MailServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private MailService mailService;
	
	public AbstractMailServiceHelper(Context<String, Object> context) {
		
		super(context);
		mailService = getContext().getBean(ApplicationConstants.MAIL_SERVICE, MailService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
