package org.corenel.services.transcoder.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.transcoder.service.TransCoderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractTransCoderServiceHelper extends GenericServiceHelper implements TransCoderServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private TransCoderService transCoderService;
	
	public AbstractTransCoderServiceHelper(Context<String, Object> context) {
		
		super(context);
		transCoderService = getContext().getBean(ApplicationConstants.TRANSCODER_SERVICE, TransCoderService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
