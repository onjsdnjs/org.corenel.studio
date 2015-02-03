package org.corenel.services.excel.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.manager.SpreadSheetWriter;

public interface ExcelDAO{
	
	public <T> void uploadExcel(List<T> t, String sqlId);
	public void downloadExcel(ExcelVO excelVO, Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw, int cnt, String sqlId) throws Exception;
	public ExcelVO checkDuplicateFileName(String sqlId, ExcelVO excelVO);

}
