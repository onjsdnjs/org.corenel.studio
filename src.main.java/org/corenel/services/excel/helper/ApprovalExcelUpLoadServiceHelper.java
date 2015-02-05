package org.corenel.services.excel.helper;

import java.util.List;
import java.util.Map;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.context.Context;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.corenel.services.excel.validator.ExcelEvent;

/**
 * ����/���� �� Ŭ����

 * @author ������
 * @description ���� ���ε� �� ������ ������ ���� ���μ����� �����ϴ� ��� �� Ŭ������ ��ӹ޾� ��� �� ���� �Ѵ�
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
		
		// ������û or ���ο�û
		if(ApplicationConstants.VAILD.equals(getExcelVO().getState()) || ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
			manager.extractExcelDataOfFile();
			if(ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
				isValidationExecute = false;
			}
		}
		// �� ������û
		/*else if(CommonConstants.REVAILD.equals(getExcelVO().getState())){
			extractExcelDataOfObject(manager.getValidList());
			manager.setEndColumIndex(getExcelVO().getEndColumIndex());
		
		// ���ο�û
		}else if(CommonConstants.APPROVE.equals(getExcelVO().getState())){
			setApproveRequestList(manager);
			isValidationExecute = false;
		}*/
		return isValidationExecute;
	}

	@Override
	public void validationSuccessed(ExcelEvent excelEvent) throws Exception {
		
		Map<String, Object> validResult = excelEvent.getValidResult();
		
		// ������û
		if(ApplicationConstants.VAILD.equals(getExcelVO().getState())){
			validResult.put("filename", getExcelVO().getFilename());
			validResult.put("validationStatus", "S");
    		return;
    		
    	// �� ������û
		}else if(ApplicationConstants.REVAILD.equals(getExcelVO().getState())){
			validResult.put("reValidationStatus", "S");
    		return;
    		
    	// ���ο�û
		}else if(ApplicationConstants.APPROVE.equals(getExcelVO().getState())){
			validResult.put("approveStatus", "S");
			uploadExcel(excelEvent);
    	}
	}

	public abstract void extractExcelDataOfObject(List<Map<String, String>> validList);
	public abstract void setApproveRequestList(ExcelUploadManager manager) throws Exception;
}
