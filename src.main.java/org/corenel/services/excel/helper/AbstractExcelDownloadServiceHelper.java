package org.corenel.services.excel.helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.context.Context;
import org.corenel.core.util.CommonUtils;
import org.corenel.core.util.HTTPHandler;
import org.corenel.services.excel.ExcelColsType;
import org.corenel.services.excel.ExcelConfigurationLoader;
import org.corenel.services.excel.manager.SpreadSheetWriter;

/**

 * @author Á¤¼ö¿ø
 */
@SuppressWarnings({"serial" })
public abstract class AbstractExcelDownloadServiceHelper extends AbstarctExcelServiceHelper{
	
	private Map<String, XSSFCellStyle> wbStyle;
	private SpreadSheetWriter sw;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int cnt;
	private String fileLocalPath;
	private String fileName;
	private String sqlId;
	
	public AbstractExcelDownloadServiceHelper(Context<String, Object> context) {
		super(context);
		
		if(getColumnKey() != null && !"".equals(getColumnKey())){
			getExcelVO().setColumnTitle(ExcelConfigurationLoader.getExcelColsInfo(getColumnKey(), ExcelColsType.KORNM));
			getExcelVO().setColumnList(ExcelConfigurationLoader.getExcelColsInfo(getColumnKey(), ExcelColsType.SaveName));
			getExcelVO().setColumnType(ExcelConfigurationLoader.getExcelColsInfo(getColumnKey(), ExcelColsType.Type));
		}
		fileLocalPath = (String)ExcelConfigurationLoader.getConfiguration("uploadDir") + File.separator;
	}
	
	public String getSqlId() {
		return sqlId;
	}
	
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	protected void setExcelProperties(Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw, int cnt){
		this.wbStyle = wbStyle;
		this.sw = sw;
		this.cnt = cnt;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getFileLocalPath() {
		return fileLocalPath;
	}

	@Override
	public void executeService() throws Exception{
		getExcelService().downloadExcel(getExcelVO(), wbStyle, sw, cnt, sqlId);
	}
	
	@Override
	public void handleService() throws Exception {
		excelHandleService();
	    downloadExcel(getResponse());
	}

	@Override
	public void downloadExcel(HttpServletResponse response) throws Exception {
		
		File file = new File(getFileLocalPath() + fileName + ".xlsx");
		FileInputStream fileIn = new FileInputStream(file);
		
		HTTPHandler.setReponseHeader(response, file, getFileName());
		CommonUtils.buildFile(response, file, fileIn, getFileLocalPath(), getFileName(), ".xlsx", true);
	}
	
	public abstract void excelHandleService() throws Exception;
}
