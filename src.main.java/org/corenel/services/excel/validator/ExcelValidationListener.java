package org.corenel.services.excel.validator;

public interface ExcelValidationListener {

	void validationSuccessed(ExcelEvent excelEvent) throws Exception;
	void validationFailed(ExcelEvent excelEvent) throws Exception;
	
}
