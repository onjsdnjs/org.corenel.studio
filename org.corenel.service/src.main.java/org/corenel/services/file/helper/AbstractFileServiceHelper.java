package org.corenel.services.file.helper;

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
		fileService = getContext().getBean(ApplicationConstants.FILE_SERVICE, FileService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
