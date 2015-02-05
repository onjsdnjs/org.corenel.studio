package org.corenel.services.excel.validator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.corenel.core.common.ApplicationConstants;
import org.corenel.services.excel.handler.ExcelUploadHandler.RowWritable;
import org.corenel.services.excel.helper.AbstractExcelUploadServiceHelper;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.springframework.ui.ModelMap;

public class ExcelValidator{
	
	/**
	 * List(Excel) Validation Check
	 * @date 12.04
	 * @author 김용주, 정수원
	 */

	public static void validate(AbstractExcelUploadServiceHelper serviceHelper, RowWritable rowWriteCallback, final ExcelUploadManager manager, 
			Map<Integer, DataInfo> columnInfo, ModelMap modelMap, boolean isValidationExecute) throws Exception {
		
		ValidationExecutor executor = new ExcelValidationExecutor();
		executor.addListener(serviceHelper);
		executor.execute(serviceHelper, rowWriteCallback,  manager, columnInfo, modelMap, isValidationExecute);
	}
	
	public static Map<String, Object> checkValidation(List<Map<String,String>> excelList, 
			Map<Integer, DataInfo> columnInfo, int startRowIndex, int startColumIndex, int endColumIndex) throws Exception{
		
		// 유효성 체크 수행 및 수행성공시 로직 수행
		// 파라미터:컬럼정보, 엑셀데이터, 데이터시작라인, 컬럼총개수, 결과리턴받을Map
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 데이터 관련 변수////////////////////////////////
		Iterator<Map<String, String>> it = excelList.iterator();
		Map<String, String> currentRecord = null;			

		Map<String, List<DataInfo>> validFinalResultMap = new HashMap<String, List<DataInfo>>(); // 클라이언트용 최종 결과
		List<List<DataInfo>> validRowSuccessResultList = new ArrayList<List<DataInfo>>(); // 로우 검증 성공 데이터
		List<List<DataInfo>> validRowFailResultList = new ArrayList<List<DataInfo>>(); // 로우 검증 실패 데이터
		List<DataInfo> vaildColumnResultList; // 컬럼 검증 실패 or 성공
		List<String> vaildRowMessageList = new ArrayList<String>(); // 로우 검증 실패
		
		// 레코드
		String colData = null;														//컬럼 데이터
		DataValidator dataValidator = new DataValidator();							//컬럼 데이터 유효성 체크
		
		// 결과 관련 변수///////////////////////////////////
		StringBuilder resultMsgBuilder = new StringBuilder();  						//로우 전체 검증 결과  메시지
		StringBuilder columnMsgBuilder = new StringBuilder();  						//컬럼 검증 결과 메시지
		boolean isSuccess = true;													//성공여부
		boolean isRecordSuccess = true;												//레코드 단위 성공여부
		int successCnt=0, failCnt=0 ,totalCnt=0, validCnt=0;						//성공 수

		// Debug변수////////////////////////////////////////
//		StringBuilder logBuilder = new StringBuilder();
		
		for(int currentLine=startRowIndex; it.hasNext() ; currentLine++, totalCnt++) {
			
			currentRecord = it.next();
			
			//레코드관련 상태유지하면 안되는 변수 초기화
			isRecordSuccess = true;
			columnMsgBuilder.setLength(0);
			validCnt = 0;
		
//			logBuilder.append("\n");
			vaildColumnResultList = new ArrayList<DataInfo>();
			DataInfo dataInfo = null;
			for(int currentColIdx=startColumIndex; currentColIdx < endColumIndex; currentColIdx++, validCnt++) {
				dataInfo = columnInfo.get(validCnt);
				
				//유효성 체크 위한 데이터 설정.
				colData = (String)currentRecord.get(ApplicationConstants.COLUMN_TITLE+currentColIdx);
				colData = colData == null ? "":colData;
				dataInfo.setDataValue(colData);
//				logBuilder.append("[col:").append(currentColIdx+1).append("][data:").append(colData).append("]");
				
				//셀단위 데이터 유효성 체크
				if(!dataValidator.validate(dataInfo, validCnt)){
					isSuccess = false;
					isRecordSuccess = false;
					vaildColumnResultList.add(dataInfo.clone());
					columnMsgBuilder.append(dataValidator.getResultMsg());
					
				}else{
					vaildColumnResultList.add(dataInfo.clone());
					
				}
				//dataValidator.validate() 과정에서 변경된 데이터 반영 위해 다시 set.
				currentRecord.put("COL"+currentColIdx, dataInfo.getDataValue());  
			}
			if(isRecordSuccess) {
				successCnt ++;
				validRowSuccessResultList.add(vaildColumnResultList);
			} else {
				
				validRowFailResultList.add(vaildColumnResultList);
				vaildRowMessageList.add("엑셀 문서의 " + (currentLine+2) + " 번째 라인에서 아래 오류가 발생하였습니다.\n"+ columnMsgBuilder.toString());
				validFinalResultMap.put((currentLine+2)+"F", vaildColumnResultList);
				
				resultMsgBuilder.append("\n\n엑셀 문서의 " + (currentLine+2)).append(" 번째 라인에서 아래 오류가 발생하였습니다.\n")
					.append(columnMsgBuilder.toString());
				failCnt++;
			}
		}
		
		//log.debug(logBuilder.toString());
		
		resultMap.put("SUCCESSYN", isSuccess ? "Y" : "N");
		resultMap.put("ERRORMSG", resultMsgBuilder.toString());
		resultMap.put("SUCCESSCNT", ""+successCnt);
		resultMap.put("FAILCNT", failCnt);
		resultMap.put("TOTALCNT", totalCnt);
		resultMap.put("isValidSuccess", isSuccess);
		resultMap.put("validFinalResultMap", validFinalResultMap);
		resultMap.put("validRowSuccessResultList", validRowSuccessResultList);
		resultMap.put("validRowFailResultList", validRowFailResultList);
		resultMap.put("vaildRowMessageList", vaildRowMessageList);
		resultMap.put("endColumIndex", endColumIndex);
		
		return resultMap;
	}
}
