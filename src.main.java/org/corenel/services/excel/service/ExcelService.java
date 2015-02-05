package org.corenel.services.excel.service;

import java.io.Serializable;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.manager.SpreadSheetWriter;

public interface ExcelService extends Serializable{

	public void uploadExcel(ExcelVO excelVO, String sqlId);
	public void downloadExcel(ExcelVO excelVO, Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw,int cnt, String sqlId) throws Exception ;
	public ExcelVO existDuplicateFileName(ExcelVO excelVO, String sqlId);
	
}
