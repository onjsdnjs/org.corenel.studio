package org.corenel.services.excel.manager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.corenel.core.common.domain.ExcelVO;

/**

 * @author Á¤¼ö¿ø
 */
public class HSSFXls extends ExcelUploadManager {

	public HSSFXls(ExcelVO excel) throws Exception {

		super(excel);
		
		HSSFWorkbook workbook = new HSSFWorkbook(getFile());
		HSSFSheet sheet = workbook.getSheetAt(0);

		setConfigExcel(sheet);
	}

}
