package org.corenel.services.batch.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.batch.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractBatchServiceHelper extends GenericServiceHelper implements BatchServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private BatchService batchService;
	
	public AbstractBatchServiceHelper(Context<String, Object> context) {
		
		super(context);
		batchService = getServiceContext().getBean(ApplicationConstants.BATCH_SERVICE, BatchService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + " handleService()");
		
	}
	
}
