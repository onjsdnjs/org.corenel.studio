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
	 * @author �����, ������
	 */

	public static void validate(AbstractExcelUploadServiceHelper serviceHelper, RowWritable rowWriteCallback, final ExcelUploadManager manager, 
			Map<Integer, DataInfo> columnInfo, ModelMap modelMap, boolean isValidationExecute) throws Exception {
		
		ValidationExecutor executor = new ExcelValidationExecutor();
		executor.addListener(serviceHelper);
		executor.execute(serviceHelper, rowWriteCallback,  manager, columnInfo, modelMap, isValidationExecute);
	}
	
	public static Map<String, Object> checkValidation(List<Map<String,String>> excelList, 
			Map<Integer, DataInfo> columnInfo, int startRowIndex, int startColumIndex, int endColumIndex) throws Exception{
		
		// ��ȿ�� üũ ���� �� ���༺���� ���� ����
		// �Ķ����:�÷�����, ����������, �����ͽ��۶���, �÷��Ѱ���, ������Ϲ���Map
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// ������ ���� ����////////////////////////////////
		Iterator<Map<String, String>> it = excelList.iterator();
		Map<String, String> currentRecord = null;			

		Map<String, List<DataInfo>> validFinalResultMap = new HashMap<String, List<DataInfo>>(); // Ŭ���̾�Ʈ�� ���� ���
		List<List<DataInfo>> validRowSuccessResultList = new ArrayList<List<DataInfo>>(); // �ο� ���� ���� ������
		List<List<DataInfo>> validRowFailResultList = new ArrayList<List<DataInfo>>(); // �ο� ���� ���� ������
		List<DataInfo> vaildColumnResultList; // �÷� ���� ���� or ����
		List<String> vaildRowMessageList = new ArrayList<String>(); // �ο� ���� ����
		
		// ���ڵ�
		String colData = null;														//�÷� ������
		DataValidator dataValidator = new DataValidator();							//�÷� ������ ��ȿ�� üũ
		
		// ��� ���� ����///////////////////////////////////
		StringBuilder resultMsgBuilder = new StringBuilder();  						//�ο� ��ü ���� ���  �޽���
		StringBuilder columnMsgBuilder = new StringBuilder();  						//�÷� ���� ��� �޽���
		boolean isSuccess = true;													//��������
		boolean isRecordSuccess = true;												//���ڵ� ���� ��������
		int successCnt=0, failCnt=0 ,totalCnt=0, validCnt=0;						//���� ��

		// Debug����////////////////////////////////////////
//		StringBuilder logBuilder = new StringBuilder();
		
		for(int currentLine=startRowIndex; it.hasNext() ; currentLine++, totalCnt++) {
			
			currentRecord = it.next();
			
			//���ڵ���� ���������ϸ� �ȵǴ� ���� �ʱ�ȭ
			isRecordSuccess = true;
			columnMsgBuilder.setLength(0);
			validCnt = 0;
		
//			logBuilder.append("\n");
			vaildColumnResultList = new ArrayList<DataInfo>();
			DataInfo dataInfo = null;
			for(int currentColIdx=startColumIndex; currentColIdx < endColumIndex; currentColIdx++, validCnt++) {
				dataInfo = columnInfo.get(validCnt);
				
				//��ȿ�� üũ ���� ������ ����.
				colData = (String)currentRecord.get(ApplicationConstants.COLUMN_TITLE+currentColIdx);
				colData = colData == null ? "":colData;
				dataInfo.setDataValue(colData);
//				logBuilder.append("[col:").append(currentColIdx+1).append("][data:").append(colData).append("]");
				
				//������ ������ ��ȿ�� üũ
				if(!dataValidator.validate(dataInfo, validCnt)){
					isSuccess = false;
					isRecordSuccess = false;
					vaildColumnResultList.add(dataInfo.clone());
					columnMsgBuilder.append(dataValidator.getResultMsg());
					
				}else{
					vaildColumnResultList.add(dataInfo.clone());
					
				}
				//dataValidator.validate() �������� ����� ������ �ݿ� ���� �ٽ� set.
				currentRecord.put("COL"+currentColIdx, dataInfo.getDataValue());  
			}
			if(isRecordSuccess) {
				successCnt ++;
				validRowSuccessResultList.add(vaildColumnResultList);
			} else {
				
				validRowFailResultList.add(vaildColumnResultList);
				vaildRowMessageList.add("���� ������ " + (currentLine+2) + " ��° ���ο��� �Ʒ� ������ �߻��Ͽ����ϴ�.\n"+ columnMsgBuilder.toString());
				validFinalResultMap.put((currentLine+2)+"F", vaildColumnResultList);
				
				resultMsgBuilder.append("\n\n���� ������ " + (currentLine+2)).append(" ��° ���ο��� �Ʒ� ������ �߻��Ͽ����ϴ�.\n")
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
