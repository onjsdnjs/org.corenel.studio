package org.corenel.core.common.helper;

import java.io.Serializable;

/**

 * @author ������
 */
public interface ServiceHelper extends Serializable, Cloneable{
	
	public void handleService() throws Exception;
	public void executeService() throws Exception;

}
