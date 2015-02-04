package org.corenel.core.util;

import java.text.NumberFormat;

public class FormatUtil {
	public static String getShowAmt(String amt) {
		long lAmt=0;
		try {
			lAmt = Long.parseLong(amt); 
		} catch(Exception e) {
			return amt;  //�������� �Ǿ������� �ٷ� ����
		}
		NumberFormat format = NumberFormat.getInstance();
    	format.setMaximumFractionDigits(3);
    	return format.format(lAmt)+" ��";
	}
	
	//�ŷ� �Ͻ� ǥ�� ���� ó��
    public static String getShowTranDate(String mxIssueDate) {
    	if(mxIssueDate != null && mxIssueDate.trim().length() >= 12) {
    		return mxIssueDate.substring(0,4) + "-" 
    		+ mxIssueDate.substring(4,6) + "-"
    		+ mxIssueDate.substring(6,8) + " "
    		+ mxIssueDate.substring(8,10) + ":"
    		+ mxIssueDate.substring(10,12) + ":"
    		+ mxIssueDate.substring(12);
    	} else {
    		return mxIssueDate;
    	}
    }
    
    /**	�Һ� (����ǥ��)	**/
    public static String getInstallmentdesc(String installment) {
    	if(installment != null) {
    		if("00".equals(installment)){
    			return "�Ͻú�";
    		} else{
    			return installment + "����";
    		}
    	} else {
    		return "";
    	}
	}
    /** ī���ȣ MASKING ó�� **/
    public static String getShowCardNo(String cardNo) {
    	if(cardNo != null && cardNo.trim().length() == 16) {
    		return cardNo.substring(0,4) + "-****-****-" + cardNo.substring(12);
    	} else {
    		return cardNo;
    	}
    }
    
    /** ī�� ��ȿ�Ⱓ MASKING ó�� **/
    public static String getShowExpDate(String ExpDate) {
    	if(ExpDate != null && ExpDate.trim().length() == 6) {
    		return ExpDate.substring(0,4) + "�� " + ExpDate.substring(4)+"��";
    	} else {
    		return ExpDate;
    	}
    }
}
