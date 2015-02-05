package org.corenel.services.excel.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Row;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.core.context.Context;
import org.corenel.core.handler.Handler;
import org.corenel.core.util.ClassUtil;
import org.corenel.services.excel.ExcelColsType;
import org.corenel.services.excel.ExcelConfigurationLoader;
import org.corenel.services.excel.handler.ExcelUploadHandler;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.corenel.services.excel.validator.DataInfo;
import org.corenel.services.excel.validator.ExcelEvent;
import org.corenel.services.excel.validator.ExcelValidationListener;
import org.corenel.services.excel.validator.ValidityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 * @author 정수원
 */
@SuppressWarnings({"serial" })
public abstract class AbstractExcelUploadServiceHelper extends AbstarctExcelServiceHelper implements ExcelValidationListener{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private String sqlId;
	private String[] fieldNames;
	private String[] fieldValues;
	private List<String[]> validationTitle;
	private List<String[]> validationInfo;
	private String originalFilename;
	private String ext;
	private List<ExcelVO> finalExcelDatas = new ArrayList<ExcelVO>();
	
	public AbstractExcelUploadServiceHelper(Context<String, Object> context) {
		super(context);

		if(getColumnKey() != null && !"".equals(getColumnKey())){
			validationTitle = ExcelConfigurationLoader.getExcelValidsInfo(getColumnKey(), ExcelColsType.TITLE);
			validationInfo = ExcelConfigurationLoader.getExcelValidsInfo(getColumnKey(), ExcelColsType.VALIDINFO);
		}
	}
	
	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	public void setFieldValues(String[] fieldValues) {
		this.fieldValues = fieldValues;
	}

	public List<ExcelVO> getExcels() {
		return finalExcelDatas;
	}

	public String getOriginalFilename(){
		return originalFilename;
	}

	public String getExt(){
		return ext;
	}
	
	protected boolean isValidationExecute(){
		return validationTitle.size() > 0 && validationInfo.size() > 0;
	}
	public String[] getFieldNames() {
		return fieldNames;
	}

	public String[] getFieldValues() {
		return fieldValues;
	}
	
	@Override
	public void handleService() throws Exception {
		
		logger.info("Excel Upload Service Start..");
		
		Handler excelHandler = new ExcelUploadHandler(this, getModel(), getExcelVO(), new ExcelUploadHandler.RowWritable() {
			
			public void writeRow() throws Exception {
				executeService();
			}
		});
		excelHandler.handleRequest();
	}
	
	@Override
	public void executeService() throws Exception{
		getExcelService().uploadExcel(getExcelVO(),sqlId);
	}
	
	@Override
	protected void uploadExcel(ExcelEvent excelEvent) throws Exception {
		if(getExcels().isEmpty() && getExcels().size() == 0){ 
			mapperRow(getExcels(), excelEvent.getManager()); // 검증단계에서 특정한 로직처리를 위해 데이터 매핑이 이미 이루어진 경우에는 이 로직을 타지 않는다
		}
		getExcelVO().setDataList(getExcels());
		excelEvent.getRowWriteCallback().writeRow();
	}
	
	protected void mapperRow(List<ExcelVO> excelList, ExcelUploadManager manager) throws Exception {
		
		List<Row> rowList = manager.getRowList();

		for (int i=0; i<rowList.size(); i++) {
			ExcelVO excel = excelToObject(rowList.get(i),manager.getStartColumIndex());
			excelList.add(excel);
		}
	}
	
	protected ExcelVO excelToObject(Row row, int startColumIndex) throws Exception{
		
		ExcelVO excelVO = (ExcelVO)ClassUtil.createInstance(getExcelVO().getClass());
		
		if(fieldNames != null){
			for(int i=0; i< fieldNames.length; i++){
			
				//  엑셀 문서의 데이터가 아닌 Controller 클래스에서 인자로 받은 값을 설정
				if(fieldValues != null && fieldValues.length > i){
					if(!"".equals(fieldValues[i])){
						getClassReflectExecutor().set(excelVO, fieldNames[i],fieldValues[i]);
					}
				//  엑셀 문서의 데이터로 값을 설정 
				}else{
					getClassReflectExecutor().set(excelVO, fieldNames[i], ExcelUploadManager.getExcelValueConvert(row.getCell(startColumIndex).getCellType(),startColumIndex, row));
					startColumIndex++;			
				}
			}
		}
    	return excelVO;
	}
	
	public Map<Integer, DataInfo> validationSettings() {

		Map<Integer, DataInfo> columnMap = new HashMap<Integer, DataInfo>();
		StringTokenizer configArray;
		
		if(validationTitle != null && validationTitle.size() > 0){
			
			for(int i=0; i< validationTitle.size(); i++){
				ValidityInfo validityInfo = new ValidityInfo();
				String[] valids = validationInfo.get(i);
					
					for(int k=0; k<valids.length; k++){
						String valid = valids[k];
						configArray = new StringTokenizer(valid, "=");
						int validSize = configArray.countTokens();
	      				String configName = (String) configArray.nextElement();
	      				
	      				if(validSize < 2) {
	      					throw new IllegalArgumentException("Format is incorrect. confirm Excel.xml document");
	      					
	      				}else{
		      				if("required".equals(configName) && "true".equals((String)configArray.nextElement())){
		      					validityInfo.setRequired();
		      				}
		      				else if("maxLength".equals(configName)){
		      					validityInfo.setMaxLength(Integer.valueOf((String)configArray.nextElement()));
		      				}
		      				else if("minLength".equals(configName)){
		      					validityInfo.setMinLength(Integer.valueOf((String)configArray.nextElement()));
		      				}
		      				else if("machedLength".equals(configName)){
		      					validityInfo.setMachedLength(Integer.valueOf((String)configArray.nextElement()));
		      				}
		      				else if("dataFormat".equals(configName)){
		      					String value = (String)configArray.nextElement();
		      					if("number".equals(value)){
		      						validityInfo.setDataFormat("number");
		      					}else if("phone".equals(value)){
		      						validityInfo.setDataFormat("phone");
		      					}else if("email".equals(value)){
		      						validityInfo.setDataFormat("email");
		      					}else if("date".equals(value)){
		      						validityInfo.setDataFormat("date");
		      					}else if("alpha".equals(value)){
		      						validityInfo.setDataFormat("alpha");
		      					}
		      				}
	      				}
					}
				columnMap.put(i,new DataInfo().setDataDesc(validationTitle.get(i)[0]).setValidityInfo(validityInfo));
			}
		}
		return columnMap;
	}
	
	@Override
	public void validationSuccessed(ExcelEvent excelEvent) throws Exception {
		uploadExcel(excelEvent);
	}

	@Override
	public void validationFailed(ExcelEvent excelEvent) throws Exception {
		excelEvent.getValidResult().put("validationStatus", "F");
		excelEvent.getValidResult().put("filename", getExcelVO().getFilename());
		return;
	}

	public void validationFileInfo(String filename){
		originalFilename = filename;
		ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
	}
	
	public void existDuplicateFileName(){};
	public abstract void existDuplicateData(ExcelUploadManager manager) throws Exception ;
	public abstract boolean extractExcelData(ExcelUploadManager manager) throws Exception;
}
