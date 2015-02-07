package org.corenel.services.ftp.helper;

import org.apache.camel.Exchange;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.Response;
import org.corenel.core.common.domain.ServiceResponse;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.common.helper.ServiceHelperHolder;
import org.corenel.core.common.pipe.Pipeline;
import org.corenel.core.context.Context;
import org.corenel.services.ftp.service.FtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author 정수원
 */
@SuppressWarnings("serial")
public abstract class AbstractFtpServiceHelper extends GenericServiceHelper implements FtpServiceHelper{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private FtpService ftpService;
	
	public AbstractFtpServiceHelper(Context<String, Object> context) {
		
		super(context);

		ftpService = getServiceContext().getBean(ApplicationConstants.FTP_SERVICE, FtpService.class);
		
	}
	
	@Override
	public void executeService() throws Exception{
	}

	@Override
	public void handleService() throws Exception {
		
		logger.info(">> " + getClass().getSimpleName() + " handleService()");
		
//		try {
			
//			executeService();
			
		/*} catch (Exception e) {
			
			Exchange exchange = getServiceContext().getBean(ApplicationConstants.EXCHANGE, Exchange.class);

			Pipeline pipeline = (Pipeline)exchange.getIn().getBody();
			
			Response response = new ServiceResponse();
			response.setMessage(new Exception("ftp: 파일 업로드에 실패하였습니다.").getMessage());
			pipeline.setResult(response);

		}
*/	}
	
}
