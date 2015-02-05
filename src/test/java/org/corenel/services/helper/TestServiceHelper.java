package org.corenel.services.helper;

import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
public class TestServiceHelper extends GenericServiceHelper {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public TestServiceHelper(Context<String, Object> context) {
		super(context);
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
	}

	@Override
	public void executeService() throws Exception {

	}

}
