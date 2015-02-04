package org.corenel.core.common.helper;

import java.io.Serializable;

/**

 * @author Á¤¼ö¿ø
 */
public interface ServiceHelper extends Serializable, Cloneable{
	
	public void handleService() throws Exception;
	public void executeService() throws Exception;

}
