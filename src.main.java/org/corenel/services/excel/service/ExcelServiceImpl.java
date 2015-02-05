package org.corenel.services.excel.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.dao.ExcelDAO;
import org.corenel.services.excel.manager.SpreadSheetWriter;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service("excelService")
public class ExcelServiceImpl implements ExcelService{
	
	@Resource(name="excelDao")
    private ExcelDAO excelDao;

	@Override
	public void uploadExcel(ExcelVO excelVO, String sqlId) {
		excelDao.uploadExcel(excelVO.getDataList(), sqlId);
	}

	@Override
	public void downloadExcel(ExcelVO excelVO, Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw, int cnt, String sqlId) throws Exception {
		excelDao.downloadExcel(excelVO, wbStyle, sw, cnt, sqlId);
	}

	@Override
	public ExcelVO existDuplicateFileName(ExcelVO excelVO, String sqlId) {
		return excelDao.checkDuplicateFileName(sqlId, excelVO);
	}
}
