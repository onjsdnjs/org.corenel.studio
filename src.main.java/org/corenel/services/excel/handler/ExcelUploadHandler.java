package org.corenel.services.excel.handler;

import java.io.IOException;
import java.util.Map;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.ServiceExceptionResolver;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.core.handler.AbstractHandler;
import org.corenel.services.excel.helper.AbstractExcelUploadServiceHelper;
import org.corenel.services.excel.manager.ExcelUploadManager;
import org.corenel.services.excel.manager.HSSFXls;
import org.corenel.services.excel.manager.XSSFXlsx;
import org.corenel.services.excel.validator.DataInfo;
import org.corenel.services.excel.validator.ExcelValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**

 * @author ������
 */
public class ExcelUploadHandler extends AbstractHandler implements ExcelHandler {
	
	Logger logger =  LoggerFactory.getLogger(ExcelUploadHandler.class);
	
	private ExcelVO excelVO;
	private ModelMap modelMap;
	private RowWritable rowWriteCallback;
	private AbstractExcelUploadServiceHelper serviceHelper;
	
	public interface RowWritable {
		public void writeRow() throws Exception;
	}
	
	public ExcelUploadHandler(AbstractExcelUploadServiceHelper helper, ModelMap model, ExcelVO excel, RowWritable rowWrite) throws IOException {
		modelMap = model;
		excelVO = excel;
		rowWriteCallback = rowWrite;
		serviceHelper = helper;
	}

	@Override
	public void handleRequest() throws Exception {
		
		ExcelUploadManager manager = null;
		CommonsMultipartFile mFile = excelVO.getFile();
		
		if(mFile != null){
			
			serviceHelper.validationFileInfo(mFile.getOriginalFilename());
			
			// ���ϸ� �ߺ�üũ
			serviceHelper.existDuplicateFileName();
			
			if(ApplicationConstants.EXCEL_TYPE_XLS.equals(serviceHelper.getExt())){
				manager = new HSSFXls(excelVO);
				
			}else if(ApplicationConstants.EXCEL_TYPE_XLSX.equals(serviceHelper.getExt())){
				manager = new XSSFXlsx(excelVO);
				
			}else{
				throw new ServiceExceptionResolver("It is unSupported file");
			}
		}else{
			manager = new ExcelUploadManager();
		}
		
		// ���� �÷� ���� validation ���� 
		Map<Integer, DataInfo> columnInfo = serviceHelper.validationSettings();

		// ���� ������ ���� �� ����
        boolean isValidationExecute = serviceHelper.extractExcelData(manager);
        
        // ���� �÷� �� ������ validation ����
        ExcelValidator.validate(serviceHelper, rowWriteCallback, manager, columnInfo, modelMap, isValidationExecute);
	}
}
