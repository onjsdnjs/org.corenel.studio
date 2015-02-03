package org.corenel.services.excel.helper.impl.upload;

import org.corenel.core.context.Context;
import org.corenel.services.excel.helper.AbstractExcelUploadServiceHelper;
import org.corenel.services.excel.manager.ExcelUploadManager;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public class DefaultExcelUpLoadServiceHelper extends AbstractExcelUploadServiceHelper{
	
	public DefaultExcelUpLoadServiceHelper(Context<String, Object> context) {
		super(context);
	}

	@Override
	public boolean extractExcelData(ExcelUploadManager manager) throws Exception {
		manager.extractExcelDataOfFile();
		return isValidationExecute();
	}
	
	@Override
	public void existDuplicateData(ExcelUploadManager manager) throws Exception {}

	@Override
	public DefaultExcelUpLoadServiceHelper clone() throws CloneNotSupportedException {
		return (DefaultExcelUpLoadServiceHelper)super.clone();
	}
	
	
	
}
