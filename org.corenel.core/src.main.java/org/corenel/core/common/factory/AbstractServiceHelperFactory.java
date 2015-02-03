package org.corenel.core.common.factory;

import org.corenel.core.context.Context;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstractServiceHelperFactory implements ServiceHelperFactory {

	public abstract void createServiceHelper(Context<String, Object> context) throws Exception;
	
}
