package org.corenel.core.common.factory;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;

/**
 * @author Á¤¼ö¿ø
 */

@SuppressWarnings({"serial","unchecked"})
@Component("serviceHelperFactory")
public class DefaultServiceHelperFactory extends AbstractServiceHelperFactory {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void initializeServiceHelper(Context<String, Object> context) throws Exception{
		
		logger.info("DefaultServiceHelperFactory initializeServiceHelper()..");

		setServiceContext(context);
	
		List<String> services = context.getBean(ApplicationConstants.SERVICE_CLASSES, List.class);
		if(services != null && services.size() > 0){
			for (int i = 0; i < services.size(); i++){
				Class<? extends GenericServiceHelper> clazz = (Class<? extends GenericServiceHelper>) Class.forName(services.get(i));
				createServiceHelper(clazz);
			}
		}

		String dispatcher = context.getBean(ApplicationConstants.SERVICE_DISPATCHER, String.class);
		if(dispatcher != null){
			Class<? extends GenericServiceHelper> clazz = (Class<? extends GenericServiceHelper>)Class.forName(dispatcher);
			createServiceHelper(clazz);
		}
	}

	@Override
	public void createServiceHelper(Class<? extends GenericServiceHelper> clazz) throws Exception {
		
		Constructor<?> service = clazz.getConstructor(getParamTypes());
		Object newInstance = service.newInstance(getArgs());
		getServiceContext().putBean(newInstance.getClass().getName(), newInstance);
		
		logger.info("{} has created in serviceContext" , newInstance.getClass().getName());
	}
}
