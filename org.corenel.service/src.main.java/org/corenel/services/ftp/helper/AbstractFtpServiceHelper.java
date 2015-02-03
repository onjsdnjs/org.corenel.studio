package org.corenel.services.ftp.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.ftp.service.FtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractFtpServiceHelper extends GenericServiceHelper implements FtpServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private FtpService ftpService;
	
	public AbstractFtpServiceHelper(Context<String, Object> context) {
		
		super(context);

		ftpService = getContext().getBean(ApplicationConstants.FTP_SERVICE, FtpService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
