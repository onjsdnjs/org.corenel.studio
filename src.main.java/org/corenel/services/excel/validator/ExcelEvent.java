package org.corenel.services.excel.validator;

import java.util.Map;
import org.corenel.services.excel.handler.ExcelUploadHandler.RowWritable;
import org.corenel.services.excel.manager.ExcelUploadManager;

public class ExcelEvent {
	
	private Map<String, Object> validResult;
	private RowWritable rowWriteCallback;
	private ExcelUploadManager manager;

	public Map<String, Object> getValidResult() {
		return validResult;
	}
	public void setValidResult(Map<String, Object> validResult) {
		this.validResult = validResult;
	}
	public RowWritable getRowWriteCallback() {
		return rowWriteCallback;
	}
	public void setRowWriteCallback(RowWritable rowWriteCallback) {
		this.rowWriteCallback = rowWriteCallback;
	}

	public ExcelUploadManager getManager() {
		return manager;
	}
	public void setManager(ExcelUploadManager manager) {
		this.manager = manager;
	}
}
