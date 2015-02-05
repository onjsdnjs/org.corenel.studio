package org.corenel.services.excel.handler;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.excel.ExcelConfigurationLoader;
import org.corenel.services.excel.manager.ExcelDownloadManager;
import org.corenel.services.excel.manager.ExcelStyler;
import org.corenel.services.excel.manager.SpreadSheetWriter;


public class ExcelDownloadHandler extends AbstractHandler implements ExcelHandler {
	
	private String fileName;
	private RowReadable rowReadCallback;

	public interface RowReadable {
		public void readRow(SpreadSheetWriter sw, Map<String, XSSFCellStyle> wbStyle) throws Exception;
	}

	public ExcelDownloadHandler(String fname, RowReadable callback) throws IOException {
		fileName = fname;
		rowReadCallback = callback;
	}
	
	public void handleRequest() throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Sheet");
		String sheetRef = sheet.getPackagePart().getPartName().getName();
		Map<String, XSSFCellStyle> wbStyle = ExcelStyler.createStyles(wb);
		
		String uploadDir = (String)ExcelConfigurationLoader.getConfiguration("uploadDir") + File.separator;
		makeTempExcel(wb,uploadDir);
		
		String fileLocalPath = (String)ExcelConfigurationLoader.getConfiguration("uploadDir") + File.separator;
		File tempXmlFile = new File(fileLocalPath + File.separator + "temp.xml");
//		File tempXmlFile = File.createTempFile("temp", ".xml");
//		log.debug("Temp XML File Path:"+tempXmlFile.getPath());
        
		makeTempXml(tempXmlFile, wbStyle);
        
		// 이 클래스에 추가적인 전역변수를 두어서는 안됩니다. 만약 필요할 경우에는 싱글톤이 아닌 클래스로 변경해야 합니다.
        ExcelDownloadManager manager = ExcelDownloadManager.getInstance();
        manager.makeTargetExcel(tempXmlFile, sheetRef, getTempExcelPath(uploadDir), getTargetExcelPath(uploadDir));
	}
	
	private void makeTempExcel(XSSFWorkbook wb, String uploadDir) throws Exception {
		FileOutputStream os = null;
        try{
            os = new FileOutputStream(getTempExcelPath(uploadDir));
            wb.write(os);
        }finally{
            os.close();
        }
	}
	
	private void makeTempXml(File tempXmlFile, Map<String, XSSFCellStyle> wbStyle) throws Exception {
        //tmp.deleteOnExit(); //서버 리스타트가 되어야 삭제되어 주석처리
        Writer fw = null;
        
        fw = new OutputStreamWriter(new FileOutputStream(tempXmlFile), "UTF-8") ;
        SpreadSheetWriter sw = new SpreadSheetWriter(fw);
        sw.beginSheet();	 //엑셀시트 생성시작 
		
		if(rowReadCallback != null) {
			rowReadCallback.readRow(sw, wbStyle);
		} else {
			throw new Exception("Need implement RowWritable in Constructor for reading Excel Rows.");
		}
		
        sw.endSheet();	 //엑셀시트 생성종료
        fw.close();
	}
	
	private String getTempExcelPath(String uploadDir) {
		String tempFileName = fileName +"_temp";
        return uploadDir + File.separator + tempFileName + ".xlsx";
	}
	
	private String getTargetExcelPath(String uploadDir) {
		return uploadDir + File.separator + fileName + ".xlsx";
	}

	public void setRowReadCallback(RowReadable rowReadCallback) {
		this.rowReadCallback = rowReadCallback;
	}

	public RowReadable getRowReadCallback() {
		return rowReadCallback;
	}
}
