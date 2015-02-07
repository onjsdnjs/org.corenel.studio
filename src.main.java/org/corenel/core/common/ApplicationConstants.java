package org.corenel.core.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ApplicationConstants implements Serializable{
	
	public static final String PAGE_SIZE = "500";
	public static final String CARD = "card";
	public static final String HP = "hp";
	public static final String ACCT = "acct";
	public static final String VACT = "vact";
	public static final String PNT = "pnt";
	public static final String RESPTYPE_ALL = "all";
	public static final String RESPTYPE_FAIL = "fail";
	public static final String CASH = "cash";
	public static final String TRAN = "Tran";
	public static final String MCT = "Mct";
	public static final String VAILD = "valid";
	public static final String REVAILD = "revalid";
	public static final String APPROVE = "approve";
	public static final String EMPTY = "";
	public static final String FILE_NAME = "fileName";
	public static final String SAMPLE_NAME = "sampleName";
	public static final String SQL_ID = "sqlId";
	public static final String CONTEXT = "context";
	public static final String PROPERTIES_SERVICE = "propertiesService";
	public static final String TRAN_SERVICE = "tranService";
	public static final String MESSAGE_RESULT_VO = "resultVO";
	public static final String COMMAND_MODEL_VO = "modelVO";
	public static final String EXCEL_MODEL_VO = "excelVO_";
	public static final String RESPONSE = "response";
	public static final String REQUEST = "request";
	public static final String SERVICE_CLASSES = "serviceClasses";
	public static final String SERVICE_DISPATCHER= "serviceDispatcher";
	public static final String INTERWORKING_CLASS_TYPE = "interWorkingClassType";
	public static final String SERVICE_CLASS_TYPE = "serviceClassType";
	public static final String EXCHANGE = "exchange";
	public static final String SERVICE_FACTORY = "serviceFactory";
	public static final String MODEL_MAP = "modelMap";
	public static final String COLUMN_CONFIG = "columnKey";
	public static final String PARAMS = "params_";
	public static final String DISRUPTOR = "disruptor";
	public static final String FIELD_NAMES = "fieldNames";
	public static final String FIELD_VALUES = "fieldValues";
	public static final String VALIDATION_TITLE = "validationTitle";
	public static final String VALIDATION_INFO = "validationInfo";

	public static final String EVENT_HANDLER_CHAIN = "eventHandlerChain";

	public static final String TRAN_CRDT_SUCCESS = "Tran.Crdt.Success";
	public static final String TRAN_HP_SUCCESS = "Tran.Hp.Success";
	public static final String TRAN_ACCT_SUCCESS = "Tran.Acct.Success";
	public static final String TRAN_VACT_SUCCESS = "Tran.Vact.Success";
	public static final String TRAN_PNT_SUCCESS = "Tran.Pnt.Success";
	public static final String TRAN_BILL_CUST = "Tran.BillCust";
	public static final String TRAN_BILL_REQUEST = "Tran.BillRequest";
	public static final String TRAN_BILL_CUST_INFO = "Tran.BillCustInfo";
	public static final String VALID_PRODUCT = "Valid.Product";
	public static final String VALID_STAFF = "Valid.Staff";
	public static final String VALID_BILLREQ = "Valid.Billreq";
	public static final String VALID_BRANCH = "Valid.Branch";
	public static final String VALID_CARD_APPROVAL = "Valid.Card.Approval";
	
	
	public static final String EXCEL_SERVICE = "excelService";
	public static final String MESSAGE_SERVICE = "messageService";
	public static final String BATCH_SERVICE = "batchService";
	public static final String FTP_SERVICE = "ftpService";
	public static final String FILE_SERVICE = "fileService";
	public static final String MAIL_SERVICE = "mailService";
	public static final String MONITOR_SERVICE = "monitorService";
	public static final String PUSH_SERVICE = "pushService";
	public static final String TRANSCODER_SERVICE = "transCoderService";
	public static final String TCP_SERVICE = "tcpService";
	public static final String UDP_SERVICE = "udpService";
	public static final String JMS_SERVICE = "jmsService";
	public static final String WEBSOCKET_SERVICE = "webSocketService";

	public static final String TRAN_CRDT_FAIL = "Tran.Crdt.Fail";
	public static final String TRAN_HP_FAIL = "Tran.Hp.Fail";
	public static final String TRAN_ACCT_FAIL = "Tran.Acct.Fail";
	public static final String TRAN_VACT_FAIL = "Tran.Vact.Fail";
	public static final String TRAN_PNT_FAIL = "Tran.Pnt.Fail";

	public static final String COLUMN_TITLE = "COL";
	public static final String COLUMN_TYPE = "TYPE";
	public static final String EXCEL_TYPE_XLS = "xls";
	public static final String EXCEL_TYPE_XLSX = "xlsx";
	
	public static final String EXCEL_UPLOAD_STAFF_SQLID = MCT + ".insertStaffInfomation";
	public static final String EXCEL_UPLOAD_PRODUCT_SQLID = MCT + ".insertProduct";
	public static final String EXCEL_UPLOAD_BILLREQUEST_SQLID = TRAN + ".insertBillRequest";
	public static final String EXCEL_UPLOAD_BRANCHINFO_SQLID = MCT + ".insertBranchInfo";
	
	public static final String EXCEL_DOWNLOAD_CRDT_SQLID = TRAN + ".downloadExcelTranCard";
	public static final String EXCEL_DOWNLOAD_HP_SQLID = TRAN + ".downloadExcelTranHp";
	public static final String EXCEL_DOWNLOAD_ACCT_SQLID = TRAN + ".downloadExcelTranAcct";
	public static final String EXCEL_DOWNLOAD_VACT_SQLID = TRAN + ".downloadExcelTranVact";
	public static final String EXCEL_DOWNLOAD_PNT_SQLID = TRAN + ".downloadExcelTranPnt";
	public static final String EXCEL_DOWNLOAD_ESCROW_SQLID = TRAN + ".downloadExcelTranEscrow";
	public static final String EXCEL_DOWNLOAD_CASH_SQLID = TRAN + ".downloadExcelTranCash";
	public static final String EXCEL_DOWNLOAD_BILLCUST_SQLID = TRAN + ".downloadExcelTranBillCust";
	public static final String EXCEL_DOWNLOAD_BILLREQUEST_SQLID = TRAN + ".downloadExcelTranBillRequest";
	public static final String EXCEL_DOWNLOAD_BILLCUSTINFO_SQLID = TRAN + ".selectBillCustInfoListForExcelDown";

	public static final String EXCEL_DUPLICATE_FILENAME_SQLID = TRAN + ".checkDuplicateFileName";

}
