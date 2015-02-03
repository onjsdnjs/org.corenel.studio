package org.corenel.services.excel.dao;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.common.dao.CommonDAOImpl;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.handler.ExcelDownloadRowHandler;
import org.corenel.services.excel.manager.SpreadSheetWriter;
import org.springframework.stereotype.Repository;

@SuppressWarnings("serial")
@Repository("excelDao")
public class ExcelDAOImpl extends CommonDAOImpl implements ExcelDAO{

	@Override
	public <T> void uploadExcel(List<T> t, String sqlId) {
		super.batchUpdate(t, sqlId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void downloadExcel(ExcelVO excelVO, Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw, int cnt, String sqlId) throws Exception {
		
		ExcelDownloadRowHandler rowHandler = new ExcelDownloadRowHandler(excelVO, wbStyle, sw, cnt);
//		getSqlMapClient().queryWithRowHandler(sqlId, excelVO, rowHandler);
	}

	@Override
	public ExcelVO checkDuplicateFileName(String sqlId, ExcelVO excelVO) {
//		return (ExcelVO) selectByPk(sqlId, excelVO);
		return null;
	}

}
