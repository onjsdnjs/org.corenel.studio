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
public class DefaultServiceHelperFactory implements ServiceHelperFactory {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name = "serviceContext")
    private Context<String, Object> serviceContext;

	@Override
	public void createServiceHelper(Context<String, Object> context) throws Exception{
		
		logger.info("DefaultServiceHelperFactory createServiceHelper()..");
		
		Class<?>[] paramTypes = {Context.class};
		String[] keys = {ApplicationConstants.CONTEXT};
		Object[] args =  context.getBeans(keys, paramTypes);
		
		List<String> services = context.getBean(ApplicationConstants.SERVICE_CLASSES, List.class);
		if(services != null && services.size() > 0){
			for (int i = 0; i < services.size(); i++){
				Class<?> clazz = Class.forName(services.get(i));
				Constructor<?> service = clazz.getConstructor(paramTypes);
				Object newInstance = service.newInstance(args);
				context.putBean(newInstance.getClass().getName(), newInstance);
				
				logger.info("{} has created in serviceContext" , newInstance.getClass().getName());
				
			}
		}

		String dispatcher = context.getBean(ApplicationConstants.SERVICE_DISPATCHER, String.class);
		if(dispatcher != null){
			Class<?> clazz = Class.forName(dispatcher);
			Constructor<?> serviceDisruptor = clazz.getConstructor(paramTypes);
			Object newInstance = serviceDisruptor.newInstance(args);
			context.putBean(newInstance.getClass().getName(), newInstance);
			
			logger.info("ServiceDispatcher has created({})", newInstance.getClass().getName());
		}
	}

	@Override
	public void createServiceHelper(Class<? extends GenericServiceHelper> clazz) throws Exception {
		
		Class<?>[] paramTypes = {Context.class};
		String[] keys = {ApplicationConstants.CONTEXT};
		Object[] args =  serviceContext.getBeans(keys, paramTypes);
		
		Constructor<?> service = clazz.getConstructor(paramTypes);
		Object newInstance = service.newInstance(args);
		serviceContext.putBean(newInstance.getClass().getName(), newInstance);
		
		logger.info("{} has created in serviceContext" , newInstance.getClass().getName());
	}
}
