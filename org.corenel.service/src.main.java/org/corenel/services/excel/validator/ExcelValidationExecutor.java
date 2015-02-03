package org.corenel.services.excel.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.corenel.services.excel.handler.ExcelUploadHandler.RowWritable;
import org.corenel.services.excel.helper.AbstractExcelUploadServiceHelper;
import org.corenel.services.excel.manager.ExcelUploadManager;

/**

 * @author ������
 */
public class ExcelValidationExecutor implements ValidationExecutor {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelValidationExecutor.class);
	private Map<String, Object> validResult = new HashMap<String, Object>();
	private List<ExcelValidationListener> listeners = new ArrayList<ExcelValidationListener>();
	
	@Override
	public void addListener(ExcelValidationListener listener){
		listeners.add(listener);
	}
	
	@Override
	public void execute(AbstractExcelUploadServiceHelper serviceHelper, RowWritable rowWriteCallback, ExcelUploadManager manager, Map<Integer, DataInfo> columnInfo, ModelMap modelMap, boolean isValidationExecute) throws Exception{
		
		boolean isValidSuccess = true;
		ExcelEvent excelEvent = new ExcelEvent();
		
		// ���� ���� (�� ������ �ʿ����� ���� ��� ��ŵ�Ѵ�)
		if(isValidationExecute){
			
			serviceHelper.existDuplicateData(manager); // ���� ������ �ߺ�üũ
			validResult = ExcelValidator.checkValidation(manager.getValidList(),columnInfo, manager.getStartRowIndex(), manager.getStartColumIndex(), manager.getEndColumIndex());
			isValidSuccess = (Boolean)validResult.get("isValidSuccess");
		}
		
		modelMap.addAttribute("excelResult", validResult);
		excelEvent.setValidResult(validResult);
		excelEvent.setRowWriteCallback(rowWriteCallback);
		excelEvent.setManager(manager);

		if(isValidSuccess){
			fireValidationSuccessed(excelEvent);
		}else{
			fireValidationFailed(excelEvent);
		}
	}

	private void fireValidationSuccessed(ExcelEvent excelEvent) throws Exception {
		for (ExcelValidationListener listener : listeners) {
			listener.validationSuccessed(excelEvent);
		}
	}

	private void fireValidationFailed(ExcelEvent excelEvent) throws Exception {
		for (ExcelValidationListener listener : listeners) {
			listener.validationFailed(excelEvent);
		}
	}


}
