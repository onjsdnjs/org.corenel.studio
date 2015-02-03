package org.corenel.services.excel.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.ExcelConfigurationLoader;

/**

 * @author 정수원
 */
public class ExcelUploadManager {

	private ExcelVO excelVO;	
	private List<Row> rowList = new ArrayList<Row>();
	private List<ExcelVO> approveRequestList = new ArrayList<ExcelVO>();
	private List<Map<String,String>> validList = new ArrayList<Map<String,String>>();
	private FileInputStream file;
	private int startRowIndex = 0;
	private int endRowIndex;
	private int startColumIndex = 0;
	private int endColumIndex = 0;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	
	public ExcelUploadManager() {}

	public ExcelUploadManager(ExcelVO excel) throws Exception{
		excelVO = excel;
		initialize();
	}

	private void initialize() throws Exception {
		
		String fileLocalPath = (String)ExcelConfigurationLoader.getConfiguration("uploadDir") + File.separator + excelVO.getMid() + File.separator;
		File dirPath = new File(fileLocalPath);
		if(!dirPath.exists()) dirPath.mkdirs();
		String filePath = fileLocalPath + excelVO.getFile().getOriginalFilename();
		FileOutputStream outputStream = new FileOutputStream(filePath);
		outputStream.write(excelVO.getFile().getFileItem().get());
		
		file = new FileInputStream(new File(filePath));
	}
	
	public void setConfigExcel(Sheet sheet) throws Exception{
		this.sheet = sheet;
	}
	
	public Sheet getSheet() {
		return sheet;
	}

	public FileInputStream getFile() {
		return file;
	}
	
	public List<Map<String, String>> getValidList() {
		return validList;
	}
	
	public List<Row> getRowList() {
		return rowList;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public int getStartColumIndex() {
		return startColumIndex;
	}

	public int getEndColumIndex() {
		return endColumIndex;
	}
	
	public void setEndColumIndex(int endColumIndex) {
		this.endColumIndex = endColumIndex;
	}

	public List<ExcelVO> getApproveRequestList() {
		return approveRequestList;
	}
	public ExcelVO getExcelVO() {
		return excelVO;
	}

	public void extractExcelDataOfFile() throws Exception{
		
		boolean isCheckEmptyColumn = false;
		int blankColumnCount = 0;
		endRowIndex = sheet.getLastRowNum();
        for(int i = 0; i <= endRowIndex ; i++){
        	
        	row = sheet.getRow(i);
        	if(row == null){  
        		startRowIndex++; //실제 데이터가 존재하는 첫번째 Row Index를 구한다
        		continue;
        		
        	}else if(row.getRowNum() == (startRowIndex)){ // 타이틀은 제외한다.  TODO 설정으로 USE ON/OFF 할 수 있도록 한다.
        		continue;
        	}
        	
        	Map<String,String> validMap = new HashMap<String,String>();
        	endColumIndex = row.getLastCellNum();
        	blankColumnCount = 0;
        	for(int j = 0; j < endColumIndex; j++){
        		
        		cell = row.getCell(j);
        		if(cell == null){
        			if(!isCheckEmptyColumn){
        				startColumIndex++; //실제 데이터가 존재하는 첫번째 Column Index를 구한다
        			}
					continue;
				}
            
				switch(cell.getCellType()) {
                
                    case Cell.CELL_TYPE_BOOLEAN:
                    	validMap.put(ApplicationConstants.COLUMN_TITLE + j, String.valueOf(cell.getBooleanCellValue()));
                        break;
                        
                    case Cell.CELL_TYPE_NUMERIC:
                    	validMap.put(ApplicationConstants.COLUMN_TITLE + j, String.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue()));
                        break;
                        
                    case Cell.CELL_TYPE_STRING:
                    	validMap.put(ApplicationConstants.COLUMN_TITLE + j, cell.getStringCellValue());
                        break;
                        
                    case Cell.CELL_TYPE_BLANK:
                    	validMap.put(ApplicationConstants.COLUMN_TITLE + j, cell.getStringCellValue());
                    	blankColumnCount ++;
                        break;
                }
			}
        	
        	isCheckEmptyColumn = true;
        	//컬럼번호는 1부터 시작.
        	if(blankColumnCount < (endColumIndex-1)) {
        		validList.add(validMap);
        		rowList.add(row);
        	}
			file.close();
        }
	}
	
	public static String getExcelValueConvert(int type, int startColumIndex, Row row){
		
		String value = null;
		
		switch(type) {

		case Cell.CELL_TYPE_BOOLEAN:
	    	value = String.valueOf(row.getCell(startColumIndex).getBooleanCellValue());
	    	return value;
	    case Cell.CELL_TYPE_NUMERIC:
	    	value = String.valueOf(Double.valueOf(row.getCell(startColumIndex).getNumericCellValue()).longValue());
	    	return value;
	    case Cell.CELL_TYPE_STRING:
	    	value = row.getCell(startColumIndex).getStringCellValue();
	    	return value;
	    case Cell.CELL_TYPE_BLANK:
	    	return "";
	    default:
	    	return value;
		}
	}
}

