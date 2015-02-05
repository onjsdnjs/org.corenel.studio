package org.corenel.services.excel.manager;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;

/**
 * Open XML 타입의 XML 생성
 */
public class SpreadSheetWriter {
	
	private final Writer _out;
	private int _rownum;
	
	public SpreadSheetWriter(Writer out){
		_out = out;
	}
	
	/**
	 * 시트 시작
	 *
	 * @param
	 */
	public void beginSheet() throws IOException{
		_out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		_out.write("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">\n");
		_out.write("<sheetData>\n");
	}
	
	/**
	 * 시트 시작 (컬럼 width 정보 포함)
	 *
	 * @param 컬럼정보
	 */
	public void beginSheetWithColInfo(String col_info) throws IOException{
		_out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		_out.write("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">\n");
		_out.write(col_info);
		_out.write("<sheetData>\n");
	}
	
	/**
	 * 시트 종료
	 *
	 * @param
	 */
	public void endSheet() throws IOException{
		_out.write("</sheetData>");
		_out.write("</worksheet>");
	}
	
	/**
	 * row 생성시작
	 *
	 * @param row sequence
	 */
	public void insertRow(int rownum) throws IOException{
		_out.write("<row r=\""+(rownum+1)+"\">\n");
		this._rownum = rownum;
	}
	
	/**
	 * row 생성종료
	 *
	 * @param
	 */
	public void endRow() throws IOException{
		_out.write("</row>\n");
	}
	
	/**
	 * Cell 생성
	 *
	 * @param 컬럼순서
	 * @param 컬럼 value (String)
	 * @param 스타일
	 */
	public void createCell(int columnIndex, String value, int styleIndex) throws IOException{
		String ref = new CellReference(_rownum, columnIndex).formatAsString();
		_out.write("<c r=\""+ref+"\" t=\"inlineStr\"");
		if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
		_out.write(">");
		_out.write("<is><t>"+value+"</t></is>");
		_out.write("</c>");
	}
	
	/**
	 * Cell 생성
	 *
	 * @param 컬럼순서
	 * @param 컬럼 value
	 */
	public void createCell(int columnIndex, String value) throws IOException {
		createCell(columnIndex, value, -1);
	}
	
	/**
	 * Cell 생성
	 *
	 * @param 컬럼순서
	 * @param 컬럼 value (double)
	 * @param 스타일
	 */
	public void createCell(int columnIndex, double value, int styleIndex) throws IOException{
		String ref = new CellReference(_rownum, columnIndex).formatAsString();
		_out.write("<c r=\""+ref+"\" t=\"n\"");
		if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
		_out.write(">");
		_out.write("<v>"+value+"</v>");
		_out.write("</c>");
	}
	
	/**
	 * Cell 생성
	 *
	 * @param 컬럼순서
	 * @param 컬럼 value (double)
	 */
	public void createCell(int columnIndex, double value) throws IOException{
		createCell(columnIndex, value, -1);
	}
	
	/**
	 * Cell 생성
	 *
	 * @param 컬럼순서
	 * @param 컬럼 value (Calendar)
	 * @param 스타일
	 */
	public void createCell(int columnIndex, Calendar value, int styleIndex) throws IOException {
		createCell(columnIndex, DateUtil.getExcelDate(value, false), styleIndex);
	}
}
