package org.corenel.services.messaging.sentence;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SendResultVO implements Serializable{

	private String ReferenceNO="";
	private String AuthNO="";
	private String PayMethod="";
	private String AcqNO="";
	private String MxID="";
	private String Installment="";
	private String AcqName="";
	private String AcqCode="";
	private String AcqCD="";
	private String CcMode="";
	private String MxIssueNO="";
	private String CheckYn="";
	private String MxIssueDate="";
	private String IssName="";  //카드사명
	private String CcNO="";
	private String PZTRANNO="";
	private String IssCode="";
	private String IssCD="";
	private String TxNO="";
	private String Amount="";
	private String ReplyCode="";
	private String TID="";
	private String ReplyMessage="";
	private String CcCode="";
	private String CcExpDate="";
	private String CcNameOnCard=""; //주문자명
	private String CcProdDesc=""; //상품명
	private String ReqType="";
    private String BillType="";
    private String VAT="";
    private String salPrice="";
    private String PIDS="";
    private String UserPhone="";
    private String UserName="";
    private String UserEmail="";
	
	private String MxBizNo=""; //법인번호
	
	private int seqno=0; //일괄승인시, 순번 
	public String getReferenceNO() {
		return ReferenceNO;
	}
	public void setReferenceNO(String referenceNO) {
		ReferenceNO = referenceNO;
	}
	public String getAuthNO() {
		return AuthNO;
	}
	public void setAuthNO(String authNO) {
		AuthNO = authNO;
	}
	public String getPayMethod() {
		return PayMethod;
	}
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}
	public String getAcqNO() {
		return AcqNO;
	}
	public void setAcqNO(String acqNO) {
		AcqNO = acqNO;
	}
	public String getMxID() {
		return MxID;
	}
	public void setMxID(String mxID) {
		MxID = mxID;
	}
	public String getInstallment() {
		return Installment;
	}
	public void setInstallment(String installment) {
		Installment = installment;
	}
	public String getAcqName() {
		return AcqName;
	}
	public void setAcqName(String acqName) {
		AcqName = acqName;
	}
	public String getAcqCode() {
		return AcqCode;
	}
	public void setAcqCode(String acqCode) {
		AcqCode = acqCode;
	}
	public String getCcMode() {
		return CcMode;
	}
	public void setCcMode(String ccMode) {
		CcMode = ccMode;
	}
	public String getMxIssueNO() {
		return MxIssueNO;
	}
	public void setMxIssueNO(String mxIssueNO) {
		MxIssueNO = mxIssueNO;
	}
	public String getCheckYn() {
		return CheckYn;
	}
	public void setCheckYn(String checkYn) {
		CheckYn = checkYn;
	}
	public String getMxIssueDate() {
		return MxIssueDate;
	}
	public void setMxIssueDate(String mxIssueDate) {
		MxIssueDate = mxIssueDate;
	}
	public String getIssName() {
		return IssName;
	}
	public void setIssName(String issName) {
		IssName = issName;
	}
	public String getCcNO() {
		return CcNO;
	}
	public void setCcNO(String ccNO) {
		CcNO = ccNO;
	}
	public String getPZTRANNO() {
		return PZTRANNO;
	}
	public void setPZTRANNO(String pZTRANNO) {
		PZTRANNO = pZTRANNO;
	}
	public String getIssCode() {
		return IssCode;
	}
	public void setIssCode(String issCode) {
		IssCode = issCode;
	}
	public String getTxNO() {
		return TxNO;
	}
	public void setTxNO(String txNO) {
		TxNO = txNO;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getReplyCode() {
		return ReplyCode;
	}
	public void setReplyCode(String replyCode) {
		ReplyCode = replyCode;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getReplyMessage() {
		return ReplyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		ReplyMessage = replyMessage;
	}
	public String getCcCode() {
		return CcCode;
	}
	public void setCcCode(String ccCode) {
		CcCode = ccCode;
	}
	public String getCcExpDate() {
		return CcExpDate;
	}
	public void setCcExpDate(String ccExpDate) {
		CcExpDate = ccExpDate;
	}
	public void setAcqCD(String acqCD) {
		AcqCD = acqCD;
	}
	public String getAcqCD() {
		return AcqCD;
	}
	public void setIssCD(String issCD) {
		IssCD = issCD;
	}
	public String getIssCD() {
		return IssCD;
	}
	
	public String getReqType() {
		return ReqType;
	}
	public void setReqType(String reqType) {
		ReqType = reqType;
	}
	public String getBillType() {
		return BillType;
	}
	public void setBillType(String billType) {
		BillType = billType;
	}
	public String getVAT() {
		return VAT;
	}
	public void setVAT(String vAT) {
		VAT = vAT;
	}
	public String getSalPrice() {
		return salPrice;
	}
	public void setSalPrice(String salPrice) {
		this.salPrice = salPrice;
	}
	public String getPIDS() {
		return PIDS;
	}
	public void setPIDS(String pIDS) {
		PIDS = pIDS;
	}
	public String getUserPhone() {
		return UserPhone;
	}
	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	@Override
	public String toString() {
		return "SendResultVO [ReferenceNO=" + ReferenceNO + ", AuthNO=" + AuthNO + ", PayMethod=" + PayMethod
				+ ", AcqNO=" + AcqNO + ", MxID=" + MxID + ", Installment=" + Installment + ", AcqName=" + AcqName
				+ ", AcqCode=" + AcqCode + ", AcqCD=" + AcqCD + ", CcMode=" + CcMode + ", MxIssueNO=" + MxIssueNO
				+ ", CheckYn=" + CheckYn + ", MxIssueDate=" + MxIssueDate + ", IssName=" + IssName + ", CcNO=" + CcNO
				+ ", PZTRANNO=" + PZTRANNO + ", IssCode=" + IssCode + ", IssCD=" + IssCD + ", TxNO=" + TxNO
				+ ", Amount=" + Amount + ", ReplyCode=" + ReplyCode + ", TID=" + TID + ", ReplyMessage=" + ReplyMessage
				+ ", CcCode=" + CcCode + ", CcExpDate=" + CcExpDate + ", MxBizNo=" + MxBizNo + "]";
	}
	public void setCcNameOnCard(String ccNameOnCard) {
		CcNameOnCard = ccNameOnCard;
	}
	public String getCcNameOnCard() {
		return CcNameOnCard;
	}
	public void setCcProdDesc(String ccProdDesc) {
		CcProdDesc = ccProdDesc;
	}
	public String getCcProdDesc() {
		return CcProdDesc;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setMxBizNo(String mxBizNo) {
		MxBizNo = mxBizNo;
	}
	public String getMxBizNo() {
		return MxBizNo;
	}
	
}
