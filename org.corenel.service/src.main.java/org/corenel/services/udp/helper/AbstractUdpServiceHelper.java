package org.corenel.services.udp.helper;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.services.udp.service.UdpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractUdpServiceHelper extends GenericServiceHelper implements UdpServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private UdpService udpService;
	
	public AbstractUdpServiceHelper(Context<String, Object> context) {
		
		super(context);
		udpService = getContext().getBean(ApplicationConstants.UDP_SERVICE, UdpService.class);
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + "handleService()");
		
	}
	
}
