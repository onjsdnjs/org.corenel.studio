package org.corenel.services.tcp.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.tcp.service.TcpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractTcpServiceHelper extends GenericServiceHelper implements TcpServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private TcpService tcpService;
	
	public AbstractTcpServiceHelper(Context<String, Object> context) {
		
		super(context);
		tcpService = getServiceContext().getBean(ApplicationConstants.TCP_SERVICE, TcpService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + " handleService()");
		
	}
	
}
