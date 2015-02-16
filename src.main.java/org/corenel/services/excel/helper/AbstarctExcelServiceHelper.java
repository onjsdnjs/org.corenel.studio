package org.corenel.services.excel.helper;

import javax.servlet.http.HttpServletResponse;

import org.corenel.core.common.ApplicationConstants;
import org.corenel.core.common.domain.ExcelVO;
import org.corenel.core.common.helper.GenericServiceHelper;
import org.corenel.core.context.Context;
import org.corenel.core.reflect.ClassGenerationExecutor;
import org.corenel.services.excel.service.ExcelService;
import org.corenel.services.excel.validator.ExcelEvent;
import org.springframework.ui.ModelMap;

/**
 * @author Á¤¼ö¿ø
 */
@SuppressWarnings("serial")
public abstract class AbstarctExcelServiceHelper extends GenericServiceHelper implements ExcelServiceHelper{
		
	private ExcelService excelService;
	private ExcelVO excelVO;
	private ModelMap model;
	private String columnKey;
	private ClassGenerationExecutor classReflectExecutor;
	
	public AbstarctExcelServiceHelper(Context<String, Object> context) {
		
		super(context);
		excelService = getServiceContext().getBean(ApplicationConstants.EXCEL_SERVICE, ExcelService.class);
	}

	protected ExcelVO getExcelVO() {
		return excelVO;
	}
	public void setExcelVO(ExcelVO excelVO){
		this.excelVO = excelVO;
		classReflectExecutor = getclassGenerationBuilder().buildClassGenerationExecutor(excelVO.getClass());
	}
	
	protected ClassGenerationExecutor getClassReflectExecutor() {
		return classReflectExecutor;
	}
	
	public ExcelService getExcelService() {
		return excelService;
	}
	
	public void setModel(ModelMap model){
		this.model = model;
	}
	public ModelMap getModel() {
		return model;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	protected void downloadExcel(HttpServletResponse response) throws Exception {}
	protected void uploadExcel(ExcelEvent excelEvent) throws Exception {}

}
