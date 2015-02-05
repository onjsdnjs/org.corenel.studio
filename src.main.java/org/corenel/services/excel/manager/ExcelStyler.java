package org.corenel.services.excel.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelStyler {

	public static Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb){
		Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
		XSSFDataFormat fmt = wb.createDataFormat();
		XSSFCellStyle style1 = wb.createCellStyle();
		XSSFFont font1 = wb.createFont();
		font1.setFontHeight(9);
		style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style1.setFont(font1);
		style1.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		style1.setDataFormat(fmt.getFormat("0.0%"));
		styles.put("percent", style1);
		
		XSSFCellStyle style2 = wb.createCellStyle();
		XSSFFont font2 = wb.createFont();
		font2.setFontHeight(9);
		style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style2.setFont(font2);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setDataFormat(fmt.getFormat("0.0X"));
		styles.put("coeff", style2);
		
		XSSFCellStyle style3 = wb.createCellStyle();
		XSSFFont font3 = wb.createFont();
		font3.setFontHeight(9);
		style3.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style3.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style3.setFont(font3);
		style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		style3.setDataFormat(fmt.getFormat("$#,##0.00"));
		styles.put("currency", style3);
		
		XSSFCellStyle style4 = wb.createCellStyle();
		XSSFFont font4 = wb.createFont();
		font4.setFontHeight(9);
		style4.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style4.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style4.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style4.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style4.setFont(font4);
		style4.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		style4.setDataFormat(fmt.getFormat("mmm dd"));
		styles.put("date", style4);
		
		XSSFCellStyle style5 = wb.createCellStyle();
		XSSFFont font5 = wb.createFont();
		font5.setBold(true);
		font5.setFontHeight(9);
		style5.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style5.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style5.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style5.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style5.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style5.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style5.setFont(font5);
		styles.put("header", style5);
		
		XSSFCellStyle style6 = wb.createCellStyle();
		XSSFFont font6 = wb.createFont();
		font6.setFontHeight(9);
		style6.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style6.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style6.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style6.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style6.setFont(font6);
		styles.put("data", style6);
		
		XSSFCellStyle style7 = wb.createCellStyle();
		XSSFFont font7 = wb.createFont();
		font7.setFontHeight(9);
		style7.setFont(font7);
		styles.put("title", style7);
		
		XSSFCellStyle style8 = wb.createCellStyle();
		XSSFFont font8 = wb.createFont();
		font8.setFontHeight(9);
		style8.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style8.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style8.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style8.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style8.setFont(font3);
		style8.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		style8.setDataFormat(fmt.getFormat("#,##0"));
		styles.put("number", style8);
		
		return styles;
	}
	
}
