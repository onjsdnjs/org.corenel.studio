package org.corenel.services.file.helper;

import org.apache.camel.Exchange;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractFileServiceHelper extends GenericServiceHelper implements FileServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private FileService fileService;
	
	public AbstractFileServiceHelper(Context<String, Object> context) {
		
		super(context);
		fileService = getServiceContext().getBean(ApplicationConstants.FILE_SERVICE, FileService.class);
	}
	
	@Override
	public void executeService() throws Exception{
		
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + " handleService()");
		
		Exchange exchange = getServiceContext().getBean(ApplicationConstants.EXCHANGE, Exchange.class);
		exchange.getIn().setHeader("status", "F");
		
		return;
		
	}
	
}
