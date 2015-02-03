package org.corenel.services.excel.manager;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.corenel.core.common.domain.ExcelVO;

/**

 * @author Á¤¼ö¿ø
 */
public class XSSFXlsx extends ExcelUploadManager {
	
	public XSSFXlsx(ExcelVO excel) throws Exception {
		
		super(excel);
		
		XSSFWorkbook workbook = new XSSFWorkbook(getFile());
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		setConfigExcel(sheet);
	}

}
