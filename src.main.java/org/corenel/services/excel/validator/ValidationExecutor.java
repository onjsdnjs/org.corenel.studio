package org.corenel.services.excel.validator;

import java.util.Map;

import org.corenel.services.excel.handler.ExcelUploadHandler.RowWritable;
import org.corenel.services.excel.helper.AbstractExcelUploadServiceHelper;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.springframework.ui.ModelMap;

/**

 * @author Á¤¼ö¿ø
 */
public interface ValidationExecutor {
	
	public void execute(AbstractExcelUploadServiceHelper serviceHelper, RowWritable rowWriteCallback, ExcelUploadManager manager, Map<Integer, DataInfo> columnInfo, ModelMap modelMap, boolean isExecute) throws Exception;
	public void addListener(ExcelValidationListener listener);
}
