package org.corenel.services.excel.helper;

import java.util.List;
import java.util.Map;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.context.Context;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.corenel.services.excel.validator.ExcelEvent;

/**
 * 검증/승인 용 클래스

 * @author 정수원
 * @description 엑셀 업로드 시 검증과 승인을 별도 프로세스로 진행하는 경우 이 클래스를 상속받아 사용 및 구현 한다
 * @see ExcelUpLoadBillRequestServiceHelper
 */
@SuppressWarnings("serial")
public abstract class ApprovalExcelUpLoadServiceHelper extends AbstractExcelUploadServiceHelper{

	public ApprovalExcelUpLoadServiceHelper(Context<String, Object> context) {
		super(context);
	}

	/*@Override
	public void uploadExcel(ExcelEvent excelEvent) throws Exception {
		excelEvent.getRowWriteCallback().writeRow(excelEvent.getManager().getApproveRequestList());
	}*/
	
	@Override
	public boolean extractExcelData(ExcelUploadManager manager) throws Exception {
		
		boolean isValidationExecute = isValidationExecute();
		
		// 검증요청 or 승인요청
		if(ApplicationConstants.VAILD.equals(getExcelVO().getState()) || ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
			manager.extractExcelDataOfFile();
			if(ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
				isValidationExecute = false;
			}
		}
		// 재 검증요청
		/*else if(CommonConstants.REVAILD.equals(getExcelVO().getState())){
			extractExcelDataOfObject(manager.getValidList());
			manager.setEndColumIndex(getExcelVO().getEndColumIndex());
		
		// 승인요청
		}else if(CommonConstants.APPROVE.equals(getExcelVO().getState())){
			setApproveRequestList(manager);
			isValidationExecute = false;
		}*/
		return isValidationExecute;
	}

	@Override
	public void validationSuccessed(ExcelEvent excelEvent) throws Exception {
		
		Map<String, Object> validResult = excelEvent.getValidResult();
		
		// 검증요청
		if(ApplicationConstants.VAILD.equals(getExcelVO().getState())){
			validResult.put("filename", getExcelVO().getFilename());
			validResult.put("validationStatus", "S");
    		return;
    		
    	// 재 검증요청
		}else if(ApplicationConstants.REVAILD.equals(getExcelVO().getState())){
			validResult.put("reValidationStatus", "S");
    		return;
    		
    	// 승인요청
		}else if(ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
			validResult.put("approveStatus", "S");
			uploadExcel(excelEvent);
    	}
	}

	public abstract void extractExcelDataOfObject(List<Map<String, String>> validList);
	public abstract void setApproveRequestList(ExcelUploadManager manager) throws Exception;
}
