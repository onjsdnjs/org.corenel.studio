package org.corenel.services.excel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.corenel.core.util.CommonPropertyService;
import org.springframework.stereotype.Component;

/**

 * @author 김용주, 정수원
 */
@Component("configurationLoader")
public class ExcelConfigurationLoader {

	@Resource(name = "excelColsProps")
	private Properties excelColsProps;

	/** CommonPropertyService */
    @Resource(name = "commonPropertiesService")
    private CommonPropertyService propertiesService;
	
    //Map : Excels, Columns, Attributes
	private final static Map<String, Map<String, Map<String, String>>> excelTablesMap = new HashMap<String, Map<String, Map<String, String>>>() ;
	//Map : Excels, Columns
	private final static Map<String, List<String>> allTableColumnsListMap = new HashMap<String, List<String>>() ;
	private final static Map<String, Map<String, List<String[]>>> excelValidsMap = new HashMap<String, Map<String, List<String[]>>>() ;
	private final static Map<String, Object> configurationMap = new HashMap<String,Object>() ;
	private final static Map<String, Map<String, String>> loadedExcelHeadersMap = new HashMap<String, Map<String, String>>();

	public static Object getConfiguration(String pathName) {
		return configurationMap.get(pathName);
	}

	public static List<String> getExcelColsInfo(String excelName, ExcelColsType excelColstype ) {

		Map<String, Map<String,String>> tableMap = excelTablesMap.get(excelName);
		return getColsInfo(tableMap, excelName, excelColstype);
	}
	
	public static List<String[]> getExcelValidsInfo(String excelName, ExcelColsType excelColstype ) {
		return excelValidsMap.get(excelName).get(excelColstype.toString());
	}
	

    private static List<String> getColsInfo(Map<String, Map<String,String>> tableMap, String excelName, ExcelColsType excelColstype ) {
		String colsName;
		List<String> excelColList = allTableColumnsListMap.get(excelName);
		Map<String, String> colAttrMap;
		List<String> rtnList = new ArrayList();
		for(int cnt=0; cnt < excelColList.size(); cnt++) {
			colsName = excelColList.get(cnt);
			colAttrMap = tableMap.get(colsName);
			rtnList.add(colAttrMap.get(excelColstype.toString()).replaceAll("\"", ""));
		}
		return rtnList;
	}
	
	@PostConstruct
	public void init() {
	  
	  configurationMap.put("uploadDir", propertiesService.getString("uploadDir"));
	  
	  /* 엑셀 다운로드 */
	  loadProperties(excelTablesMap, excelColsProps.getProperty("Excel.Cols.List"));
      
      /* 엑셀 업로드 Configuration  */
      StringTokenizer excelValidArray = new StringTokenizer(excelColsProps.getProperty("Excel.Valid.List"), ",");
      String validInfo=null, validKeyName=null, validKeyValue=null, validName=null, validFinalToken=null, validFinalValue=null;
      Map<String, List<String[]>> validsMap;
      
      StringTokenizer valisTokenKeys;
      StringTokenizer validTokenValue;
      StringTokenizer validInfoTokenKeys;
      String[] validFinalValueArray;
      int validSize=0;
      
      while(excelValidArray.hasMoreElements()) {
    	  
      	validInfo = (String) excelValidArray.nextElement();
      	validsMap = new HashMap<String, List<String[]>>();
      	validsMap.put(ExcelColsType.TITLE.toString(), new ArrayList<String[]>());
      	validsMap.put(ExcelColsType.VALIDINFO.toString(), new ArrayList<String[]>());
      	excelValidsMap.put(validInfo, validsMap);
      	
      	for(int validSeq=1; ;validSeq++) {
      		
      		validKeyName = validInfo+"."+validSeq;
      		validKeyValue = excelColsProps.getProperty(validKeyName);
      		
      		if(validKeyValue != null) {
      			valisTokenKeys = new StringTokenizer(validKeyValue, "|");
      			
      			while(valisTokenKeys.hasMoreElements()) {
      				
      				validTokenValue = new StringTokenizer((String)valisTokenKeys.nextElement(), ":");
      				validSize = validTokenValue.countTokens();
      				validName = (String) validTokenValue.nextElement();

      				if("TITLE".equals(validName)){
	      				if(validSize < 2) {
	      					validsMap.get(validName).add(null);
	      				} else {
	      					validFinalValueArray = new String[1];
	      					validFinalValueArray[0] = (String)validTokenValue.nextElement();
	      					validsMap.get(validName).add(validFinalValueArray);
	      				}
      				}else if("VALIDINFO".equals(validName)){
      					if(validSize < 2) {
      						validsMap.get(validName).add(null);
      					} else {
      						validFinalToken =  (String)validTokenValue.nextElement();
      						validInfoTokenKeys = new StringTokenizer(validFinalToken, ",");
      						validFinalValueArray = new String[validInfoTokenKeys.countTokens()];
      						int cnt = 0;
      						while(validInfoTokenKeys.hasMoreElements()) {
      							validFinalValue = (String)validInfoTokenKeys.nextElement();
      							validFinalValueArray[cnt]= validFinalValue;
      							cnt++;
      						}
      						validsMap.get(validName).add(validFinalValueArray);
      					}
      				}
      			}
      			
      		} else {
      			break;
      		}
      	 }
      }
	}
	
	private void loadProperties(Map<String, Map<String, Map<String,String>>> metaMap, String metaValues) {
		StringTokenizer excelColsList = new StringTokenizer(metaValues, ",");
		
	      int attrTokenSize=0;
	      while(excelColsList.hasMoreElements()) {
	    	//테이블의 모든 컬럼 유형 메타. 
	    	Map<String, Map<String,String>> colsMetaMap = new HashMap<String, Map<String, String>>();
	    	String tableName=null, columnName=null, columnAllValue=null, attrName=null, attrValue=null;
	    	
	    	//초기화. 컬럼 유형별 ArrayList 생성. 엑셀의 컬럼 순서 정보 저장.
	      	tableName = (String) excelColsList.nextElement();
	      	allTableColumnsListMap.put(tableName, new ArrayList<String>());
	      	List<String> tableColumnList = allTableColumnsListMap.get(tableName);
	      	
	      	//컬럼의 단위 속성 파싱
	      	Map<String, String> colsAttribueMap;
	      	StringTokenizer colsValuesToken;
	        StringTokenizer attributeMap;
	      	for(int colSeq=1; ;colSeq++) {
	      		columnName = tableName+"."+colSeq;
	      		columnAllValue = excelColsProps.getProperty(columnName);
	      		if(columnAllValue != null) {
	      			colsValuesToken = new StringTokenizer(columnAllValue, ",");
	      			colsAttribueMap = new HashMap<String, String>();
	      			while(colsValuesToken.hasMoreElements()) {
	      				attributeMap = new StringTokenizer((String)colsValuesToken.nextElement(), ":");
	      				attrTokenSize = attributeMap.countTokens();
	      				attrName = (String) attributeMap.nextElement();
	      				if(attrTokenSize == 2) {
	      					attrValue = (String) attributeMap.nextElement();
	      					colsAttribueMap.put(attrName, attrValue);  //단일 컬럼의 모든 속성 메타정보
	      				} else {
	      				}
	      			}
	      			tableColumnList.add(columnName); //엑셀의 컬럼 순서 정보 저장.
	      			colsMetaMap.put(columnName, colsAttribueMap); //단일 엑셀의 모든 컬럼의 속성 메타정보
	      			
	      		} else {
	      			break;
	      		}
	      	}
	      	metaMap.put(tableName, colsMetaMap);
	      }
	}	
	
	public static Map<String, String> makeGridLayoutInfo(String excelName) {
		Map<String, String> info = loadedExcelHeadersMap.get(excelName);
		if(info == null) {
			Map<String, String> headerInfo = new HashMap<String, String>();
			headerInfo.put("labelText", makeStringOfGridLabel(excelName));
			headerInfo.put("colsStr", makeStrngOfGridColsInfo(excelName));
			loadedExcelHeadersMap.put(excelName, headerInfo);
			info = headerInfo;
		}
		return info;
	}
	
	private static String makeStringOfGridLabel(String excelName) {
		List<String> korNmList = getExcelColsInfo(excelName, ExcelColsType.KORNM);
		StringBuilder sb = new StringBuilder();
		int size = korNmList.size();
		Iterator<String> itLabel = korNmList.iterator();
		for(int cnt=1; itLabel.hasNext(); cnt++) {
			sb.append(itLabel.next());
			if(cnt < size) {
				sb.append("|");
			}
		}
		return sb.toString();
	}
	
	private static String makeStrngOfGridColsInfo(String excelName) {
		Map<String, Map<String,String>> tableMap = excelTablesMap.get(excelName);
		Map<String, String> colAttrMap;
		
		
		Iterator<String> itCols = allTableColumnsListMap.get(excelName).iterator();
		String colsName;
		
		StringBuilder sb = new StringBuilder();
		int colsSize = tableMap.size();
		for(int cnt=0; itCols.hasNext(); cnt++) {
			colsName = itCols.next();
			colAttrMap = tableMap.get(colsName);
			
			Iterator<String> itAttr = colAttrMap.keySet().iterator();
			sb.append("{");
			String attrName, attrValue;
			for(int cnt2=0; itAttr.hasNext(); cnt2++) {
				attrName = itAttr.next();
				if(attrName.equals(ExcelColsType.KORNM.toString())) {
					continue;
				}
				attrValue = colAttrMap.get(attrName);
				if(cnt2 >0) {
					sb.append(",");
				}
				sb.append(attrName + ":");
				sb.append(attrValue);
			}
			sb.append("}");
			if(cnt < (colsSize-1)) {
				sb.append(",");
			}
			
		}
		return sb.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<String> itExcel = excelTablesMap.keySet().iterator();
		
		Map<String, Map<String, String>> colsMap = null;
		sb.append("================ (Start)Configuration Excel Columns Info =================\n");
		while(itExcel.hasNext()) {
			String excelName = itExcel.next();
			colsMap = excelTablesMap.get(excelName);
			Iterator<String> itCols = colsMap.keySet().iterator();
			sb.append(excelName).append("-->\n");
			while(itCols.hasNext()) {
				String colName = itCols.next();
				Map<String, String> attrMap = colsMap.get(colName);
				Iterator<String> itAttrs = attrMap.keySet().iterator();
				while(itAttrs.hasNext()) {
					String attrName = itAttrs.next();
					String attrValue = attrMap.get(attrName);
					sb.append(attrName).append("=").append(attrValue);
					sb.append("|");
				}
				sb.append("\n");
			}
		}
		sb.append("================ (End) Configuration Excel Columns Info =================\n");
		return sb.toString();
	}
}
