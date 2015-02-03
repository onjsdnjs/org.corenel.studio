package org.corenel.services.excel.helper.impl.download;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.context.Context;
import org.corenel.core.handler.Handler;
import org.corenel.services.excel.handler.ExcelDownloadHandler;
import org.corenel.services.excel.helper.AbstractExcelDownloadServiceHelper;
import org.corenel.services.excel.manager.SpreadSheetWriter;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public class DefaultExcelDownLoadServiceHelper extends AbstractExcelDownloadServiceHelper{

	public DefaultExcelDownLoadServiceHelper(Context<String, Object> context) {

		super(context);
	}
	
	public void excelHandleService() throws Exception {
		
		Handler excelHandler = new ExcelDownloadHandler(getFileName(), new ExcelDownloadHandler.RowReadable() {
			
			public void readRow(SpreadSheetWriter sw, Map<String, XSSFCellStyle> wbStyle) throws Exception {
				int cnt = 0;
				sw.insertRow(cnt); 
				
				for(int i=0; i< getExcelVO().getColumnTitle().size(); i++){
					sw.createCell(i, StringUtils.defaultString(getExcelVO().getColumnTitle().get(i), ""), wbStyle.get("header").getIndex());
		    	}

				sw.endRow();
				cnt++;
				
				setExcelProperties(wbStyle, sw, cnt);
				executeService();
			}
		});
		
	    excelHandler.handleRequest();
	}
	
}
