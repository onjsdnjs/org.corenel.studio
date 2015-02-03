package org.corenel.services.messaging.sentence;


import org.apache.commons.lang.StringUtils;
import org.corenel.core.message.CommandMessage;

@SuppressWarnings("serial")
public class SendMessageVO extends CommandMessage implements Cloneable {

	private String publicKeyPath;

	private boolean isApprove = true;
	
	//���� Header
    private String FDVer=""; //FDVer����(���� Ver.10)
    private String PGID="FDIKPG";//PGID����(���� 'FDIKPG')
    private String CryptoVer="10";//��ȣȭ��������(���� Ver.10)
    private String AlgorithmType="10";//��ȣȭ�˰����������(���� Ver.10)
    private String TID="";//����TID����(���� ���� TID)
    private String SessionKey="";//����Ű����(�������۽û���(NULL))
    private String PGIP="";//PG��������IP
    private String PGPORT="31090";//PG��������Port

    //���� Body
    private String pluginYn="N";//���� �÷����� �̻������ ���� �ʱ�ȭ
    private String CcMode="10";
    private String Tmode="WEB";
    private String Smode="";
    private String PayMethod="";
    private String Currency="KRW";
    private String paytype_desc="�ſ�ī��";
    private String TxCode="";
    private String MxID="";  //������ ���̵�
    private String MxOTP="";  //������ Ű.
//    private String TID2;
    private String MxIssueNO=""; //�ֹ���ȣ
    private String MxIssueDate="";
    private String OrderID=""; //���������(? ID?)
    private String CcNameOnCard=""; //�ֹ��ڸ�
    private String Email="";  //�ֹ��� �̸���
    private String ZipCode=""; //�ֹ��ڿ����ȣ
    private String Addr=""; //�ֹ��� �ּ�
    private String AddrExt=""; //�ֹ��� �ּ� ��
    private String PhoneNO=""; //�ֹ��� ����ó
    private String CcProdDesc=""; //��ǰ��
    private String Amount=""; //�����ݾ�
    private String CcNO=""; //ī���ȣ
    private String CcExpDate=""; //��ȿ�Ⱓ
    private String Installment=""; //�Һΰ�����
    private String MxName=""; //��������
    private String MxBizNo=""; //������ ���ι�ȣ
    private String Dmode=""; //����Ʈ����..
    
    //���԰�����û �����ʵ�
    private String ReqType="";
    private String BillType="";
    private String VAT="";
    private String salPrice="";
    private String PIDS="";
    private String UserPhone="";
    private String UserName="";
    private String UserEmail="";
    /////

    //�ϰ����� ���� �ʵ�
    private String CcExpYear="";
    private String CcExpMonth="";

    public String getFDVer() {
		return FDVer;
	}
	public void setFDVer(String fDVer) {
		FDVer = fDVer;
	}
	public String getPGID() {
		return PGID;
	}
	public void setPGID(String pGID) {
		PGID = pGID;
	}
	public String getCryptoVer() {
		return CryptoVer;
	}
	public void setCryptoVer(String cryptoVer) {
		CryptoVer = cryptoVer;
	}
	public String getAlgorithmType() {
		return AlgorithmType;
	}
	public void setAlgorithmType(String algorithmType) {
		AlgorithmType = algorithmType;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getSessionKey() {
		return SessionKey;
	}
	public void setSessionKey(String sessionKey) {
		SessionKey = sessionKey;
	}
	public String getPGIP() {
		return PGIP;
	}
	public void setPGIP(String pGIP) {
		PGIP = pGIP;
	}
	public String getPGPORT() {
		return PGPORT;
	}
	public void setPGPORT(String pGPORT) {
		PGPORT = pGPORT;
	}
	public String getPluginYn() {
		return pluginYn;
	}
	public void setPluginYn(String pluginYn) {
		this.pluginYn = pluginYn;
	}
	public String getCcMode() {
		return CcMode;
	}
	public void setCcMode(String ccMode) {
		CcMode = ccMode;
	}
	public String getTmode() {
		return Tmode;
	}
	public void setTmode(String tmode) {
		Tmode = tmode;
	}
	public String getSmode() {
		return Smode;
	}
	public void setSmode(String smode) {
		Smode = smode;
	}
	public String getPayMethod() {
		return PayMethod;
	}
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getPaytype_desc() {
		return paytype_desc;
	}
	public void setPaytype_desc(String paytype_desc) {
		this.paytype_desc = paytype_desc;
	}
	public String getTxCode() {
		return TxCode;
	}
	public void setTxCode(String txCode) {
		TxCode = txCode;
	}
	public String getMxID() {
		return MxID;
	}
	public void setMxID(String mxID) {
		MxID = mxID;
	}
	public String getMxOTP() {
		return MxOTP;
	}
	public void setMxOTP(String mxOTP) {
		MxOTP = mxOTP;
	}
	public String getMxIssueNO() {
		return MxIssueNO;
	}
	public void setMxIssueNO(String mxIssueNO) {
		MxIssueNO = mxIssueNO;
	}
	public String getMxIssueDate() {
		return MxIssueDate;
	}
	public void setMxIssueDate(String mxIssueDate) {
		MxIssueDate = mxIssueDate;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getCcNameOnCard() {
		return CcNameOnCard;
	}
	public void setCcNameOnCard(String ccNameOnCard) {
		CcNameOnCard = ccNameOnCard;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getAddr() {
		return Addr;
	}
	public void setAddr(String addr) {
		Addr = addr;
	}
	public String getAddrExt() {
		return AddrExt;
	}
	public void setAddrExt(String addrExt) {
		AddrExt = addrExt;
	}
	public String getPhoneNO() {
		return PhoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		
		PhoneNO = phoneNO;
	}
	public String getCcProdDesc() {
		return CcProdDesc;
	}
	public void setCcProdDesc(String ccProdDesc) {
		CcProdDesc = ccProdDesc;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getCcNO() {
		return CcNO;
	}
	public void setCcNO(String ccNO) {
		CcNO = ccNO;
	}
	public String getCcExpDate() {
		return CcExpDate;
	}
	public void setCcExpDate(String ccExpDate) {
		CcExpDate = ccExpDate;
	}
	public String getInstallment() {
		return Installment;
	}
	public void setInstallment(String installment) {
		Installment = installment;
	}
	public String getMxName() {
		return MxName;
	}
	public void setMxName(String mxName) {
		MxName = mxName;
	}
	public String getMxBizNo() {
		return MxBizNo;
	}
	public void setMxBizNo(String mxBizNo) {
		MxBizNo = mxBizNo;
	}
	private String toMsg(String keyStr, String val) {
		return StringUtils.isEmpty(val) ? "" : keyStr+val;
	}
	
	public String getSalPrice() {
		return salPrice;
	}

	public void setSalPrice(String salPrice) {
		this.salPrice = salPrice;
	}

	public String toHeader() {
		return "FDVer^" + FDVer + toMsg("&PGID^",PGID) + toMsg("&CryptoVer^",CryptoVer) 
			+ toMsg("&AlgorithmType^", AlgorithmType) + toMsg("&TID^",TID) + "&SessionKey^" + SessionKey 
			+ toMsg("&PGIP^",PGIP) + toMsg("&PGPORT^", PGPORT);
	}
	
	public String toBody() {
		if(isApprove) {
			return toApproveBody();
		} else {
			return toCancleBody();
		}
	}
	private String toApproveBody() {
		return "pluginYn^" + pluginYn + toMsg("|CcMode^", CcMode) + toMsg("|Tmode^", Tmode) + "|Smode^" + Smode
				+ toMsg("|PayMethod^", PayMethod) + toMsg("|Currency^", Currency) 
				+ toMsg("|paytype_desc^", paytype_desc) + toMsg("|TxCode^", TxCode) + toMsg("|MxID^", MxID) + "|MxOTP^" + MxOTP + toMsg("|TID^", TID) 
				+ toMsg("|MxIssueNO^", MxIssueNO) + toMsg("|MxIssueDate^", MxIssueDate) + toMsg("|OrderID^", OrderID) 
				+ toMsg("|CcNameOnCard^", CcNameOnCard) + toMsg("|Email^", Email) + toMsg("|ZipCode^", ZipCode) + toMsg("|Addr^", Addr) 
				+ toMsg("|AddrExt^", AddrExt) + toMsg("|PhoneNO^", PhoneNO) + toMsg("|CcProdDesc^", CcProdDesc) + toMsg("|Amount^", Amount) 
				+ toMsg("|Dmode^", Dmode) + toMsg("|CcNO^", CcNO) + toMsg("|CcExpDate^", CcExpDate) + toMsg("|Installment^", Installment) 
				+ toMsg("|MxName^", MxName) + toMsg("|MxBizNo^", MxBizNo)
		        + toMsg("|ReqType^", ReqType) + toMsg("|BillType^", BillType) + toMsg("|VAT^", getVAT())
		        + toMsg("|PIDS^", PIDS) + toMsg("|UserPhone^", UserPhone) + toMsg("|UserName^", UserName)
		        + toMsg("|UserEmail^", UserEmail);
	}
	
	private String toCancleBody() {
		return "pluginYn^" + pluginYn + toMsg("|CcMode^", CcMode) + toMsg("|Tmode^", Tmode)
				+ toMsg("|PayMethod^", PayMethod) + toMsg("|TxCode^", TxCode) 
				+ toMsg("|MxID^", MxID) + "|MxOTP^" + MxOTP + toMsg("|TID^", TID) 
				+ toMsg("|MxIssueNO^", MxIssueNO) + toMsg("|MxIssueDate^", MxIssueDate) 
				+ toMsg("|Amount^", Amount) 
				+ toMsg("|Dmode^", Dmode);
	}
	
	public String toTail() {
		// ���� tail ����
        return "";
	}
	
	public void setDmode(String dmode) {
		Dmode = dmode;
	}

	public String getDmode() {
		return Dmode;
	}

	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}

	public boolean isApprove() {
		return isApprove;
	}
	
	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	public void setReqType(String reqType) {
		ReqType = reqType;
	}

	public String getReqType() {
		return ReqType;
	}

	public void setBillType(String billType) {
		BillType = billType;
	}

	public String getBillType() {
		return BillType;
	}

	public void setPIDS(String pIDS) {
		PIDS = pIDS;
	}

	public String getPIDS() {
		return PIDS;
	}

	public void setUserPhone(String userPhone) {
		UserPhone = userPhone;
	}

	public String getUserPhone() {
		return UserPhone;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setVAT(String vAT) {
		VAT = vAT;
	}

	public String getVAT() {
		return VAT;
	}
	
	@Override
	public String toString() {
		return "SendMessageVO [publicKeyPath=" + publicKeyPath + ", isApprove=" + isApprove + ", FDVer=" + FDVer
				+ ", PGID=" + PGID + ", CryptoVer=" + CryptoVer + ", AlgorithmType=" + AlgorithmType + ", TID=" + TID
				+ ", SessionKey=" + SessionKey + ", PGIP=" + PGIP + ", PGPORT=" + PGPORT + ", pluginYn=" + pluginYn
				+ ", CcMode=" + CcMode + ", Tmode=" + Tmode + ", Smode=" + Smode + ", PayMethod=" + PayMethod
				+ ", Currency=" + Currency + ", paytype_desc=" + paytype_desc + ", TxCode=" + TxCode + ", MxID=" + MxID
				+ ", MxOTP=" + MxOTP + ", MxIssueNO=" + MxIssueNO + ", MxIssueDate=" + MxIssueDate + ", OrderID="
				+ OrderID + ", CcNameOnCard=" + CcNameOnCard + ", Email=" + Email + ", ZipCode=" + ZipCode + ", Addr="
				+ Addr + ", AddrExt=" + AddrExt + ", PhoneNO=" + PhoneNO + ", CcProdDesc=" + CcProdDesc + ", Amount="
				+ Amount + ", CcNO=" + CcNO + ", CcExpDate=" + CcExpDate + ", Installment=" + Installment + ", MxName="
				+ MxName + ", MxBizNo=" + MxBizNo + ", Dmode=" + Dmode + ", ReqType=" + ReqType + ", BillType="
				+ BillType + ", VAT=" + VAT + ", PIDS=" + PIDS + ", UserPhone=" + UserPhone + ", UserName=" + UserName
				+ ", UserEmail=" + UserEmail + "]";
	}

	public void setCcExpYear(String ccExpYear) {
		CcExpYear = ccExpYear;
	}

	public String getCcExpYear() {
		return CcExpYear;
	}

	public void setCcExpMonth(String ccExpMonth) {
		CcExpMonth = ccExpMonth;
	}

	public String getCcExpMonth() {
		return CcExpMonth;
	}

	@Override
	public SendMessageVO clone() throws CloneNotSupportedException {
		return (SendMessageVO) super.clone();
	}

}
