package org.corenel.core.util;

import java.text.NumberFormat;

public class FormatUtil {
	public static String getShowAmt(String amt) {
		long lAmt=0;
		try {
			lAmt = Long.parseLong(amt); 
		} catch(Exception e) {
			return amt;  //포맷팅이 되어있으면 바로 리턴
		}
		NumberFormat format = NumberFormat.getInstance();
    	format.setMaximumFractionDigits(3);
    	return format.format(lAmt)+" 원";
	}
	
	//거래 일시 표기 수정 처리
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
    
    /**	할부 (개월표시)	**/
    public static String getInstallmentdesc(String installment) {
    	if(installment != null) {
    		if("00".equals(installment)){
    			return "일시불";
    		} else{
    			return installment + "개월";
    		}
    	} else {
    		return "";
    	}
	}
    /** 카드번호 MASKING 처리 **/
    public static String getShowCardNo(String cardNo) {
    	if(cardNo != null && cardNo.trim().length() == 16) {
    		return cardNo.substring(0,4) + "-****-****-" + cardNo.substring(12);
    	} else {
    		return cardNo;
    	}
    }
    
    /** 카드 유효기간 MASKING 처리 **/
    public static String getShowExpDate(String ExpDate) {
    	if(ExpDate != null && ExpDate.trim().length() == 6) {
    		return ExpDate.substring(0,4) + "년 " + ExpDate.substring(4)+"월";
    	} else {
    		return ExpDate;
    	}
    }
}
