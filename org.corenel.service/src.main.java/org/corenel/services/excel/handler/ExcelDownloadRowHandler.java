package org.corenel.services.excel.handler;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.corenel.core.common.ServiceExceptionResolver;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.services.excel.manager.SpreadSheetWriter;

public class ExcelDownloadRowHandler implements ResultHandler {
	private int cnt;
	private SpreadSheetWriter shWriter;
	private Map<String, XSSFCellStyle> styles;
	private List<String> column_list;
	private List<String> column_type;
	
	public ExcelDownloadRowHandler(Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter shWriter, int cnt){
		this.shWriter = shWriter;
		this.styles = wbStyle;
		this.cnt = cnt;
	}
	
	public ExcelDownloadRowHandler(ExcelVO excelVO, Map<String, XSSFCellStyle> wbStyle, SpreadSheetWriter sw, int count){
		
		shWriter = sw;
		styles = wbStyle;
		cnt = count;
		column_list = excelVO.getColumnList();
		column_type = excelVO.getColumnType();
	}
	
	@SuppressWarnings("unchecked")
	public void handleResult(ResultContext paramResultContext){
		addRow((Map<String, Object>)paramResultContext.getResultObject()); //data »ý¼º
	}
	
	private void addRow(Map<String, Object> dataMap) {
		try{
			this.shWriter.insertRow(cnt);
			
			for(int i=0; i<column_list.size(); i++){

				String data = StringUtils.defaultString(String.valueOf(dataMap.get(column_list.get(i))).replaceAll("null", ""), "");
				if(column_type.get(i).equals("Int")){
					shWriter.createCell(i, Double.parseDouble(data), styles.get("number").getIndex());
					
				}else{
					shWriter.createCell(i, data.replaceAll("&", "&#38;").replaceAll("<", "&#60;").
											replaceAll(">", "&#62;"), styles.get("data").getIndex());
				}
			}
			shWriter.endRow();
			cnt++;
		} catch (Exception ex){
			throw new ServiceExceptionResolver(ex);
		}
	}
}
