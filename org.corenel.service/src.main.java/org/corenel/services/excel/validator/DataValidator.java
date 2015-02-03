package org.corenel.services.excel.validator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.springframework.validation.ValidationUtils;

public class DataValidator {
	
	private String dataValue = null;
	private boolean isEmpty = false;
	private String dataDesc = null;
	private ValidityInfo validityInfo = null;
	private int dataLength = 0;
	private String dataFormat = null;
	private Pattern pattern = null;
	private SimpleDateFormat dateFormat = null;

	private boolean isSuccess = true;
	private String resultMsg = null;
	
	public boolean validate(DataInfo dataInfo, int currentColIdx) throws Exception {
		
		ValidationUtils validator;
		StringBuilder resultMsgBuilder = new StringBuilder();
		
		//���������� �ϸ� �ȵǴ� �͵��� ���⼭ �ʱ�ȭ�Ѵ�.
		isSuccess = true;
		resultMsg = null;
		resultMsgBuilder.setLength(0);
		dataValue = dataInfo.getDataValue();
		dataDesc = dataInfo.getDataDesc();
		validityInfo = dataInfo.getValidityInfo();
		if(!dataInfo.isAcceptNull()) validityInfo.setRequired();  //Null����� �ƴϸ� �ʼ� üũ Ȱ��ȭ.
		dataLength = dataValue.getBytes().length;
		isEmpty = dataLength == 0 ? true : false;
		////////////////////////////////////////////////////////
		
		dataInfo.setValidCode("pass");
		//1. required
		if(isEmpty && validityInfo.isRequired()) {
			isSuccess = false;
//			resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� �ʼ� �Է��ϼž� �մϴ�.\n");
			resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� �ʼ� �Է��ϼž� �մϴ�.\n");
			dataInfo.setValidCode("required");
		}
		//2. matchedlength
		if(!isEmpty && validityInfo.getMachedLength() > 0 && dataLength != validityInfo.getMachedLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ����("+ dataLength +")�� ��������("+ validityInfo.getMaxLength() +")�� ���� �ʽ��ϴ�.\n");
			dataInfo.setValidCode("machedLength");
		}
		//3. maxlength
		if(!isEmpty && validityInfo.getMaxLength() > 0 && dataLength > validityInfo.getMaxLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ����("+ dataLength +")�� �ִ����("+ validityInfo.getMaxLength() +")�� �ʰ��Ͽ����ϴ�.\n");
			dataInfo.setValidCode("maxlength");
		}
		//4. minlength
		if(validityInfo.getMinLength() > 0 && dataLength < validityInfo.getMinLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ����("+ dataLength +")�� �ּұ���("+ validityInfo.getMinLength() +")���� �۽��ϴ�.\n");
			dataInfo.setValidCode("minlength");
		}
		//5. compare
		if(validityInfo.getCompareGroup() != null && validityInfo.getCompareGroup().size() > 0) {
			if(validityInfo.getCompareGroup().get(dataValue) == null) {
				isSuccess = false;
				resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� �����Ͱ� ���а��� �����ϴ�.\n");
			} else {
				dataInfo.setDataValue(validityInfo.getCompareGroup().get(dataValue));
			}
		}
		//6. format
		if(!isEmpty && (dataFormat = validityInfo.getDataFormat()) != null) {
			if(dataFormat.equals("email")) {
				pattern = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� �̸��� ���Ŀ� ���� �ʽ��ϴ�.\n");
					dataInfo.setValidCode("email");
				}
			}
			if(dataFormat.equals("date")) {
				dateFormat = new SimpleDateFormat(validityInfo.getDateFormat());
				try {
					dateFormat.parse(dataValue);
				} catch (ParseException e) {
					e.printStackTrace();
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ��¥ ����("+ validityInfo.getDateFormat() +")�� ���� �ʽ��ϴ�.\n");
					dataInfo.setValidCode("date");
				}
			}
			if(dataFormat.equals("number")) {
				pattern = Pattern.compile("^[0-9]*$");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ���� ���Ŀ� ���� �ʽ��ϴ�.\n");
					dataInfo.setValidCode("number");
				}
			}

			if(dataFormat.equals("double")) {
				pattern = Pattern.compile("\\d+\\.\\d+");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ���� ���Ŀ� ���� �ʽ��ϴ�.\n");
				}
			}
			if(dataFormat.equals("alpha")) {
				pattern = Pattern.compile("^[A-Za-z]*$");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ���� ���Ŀ� ���� �ʽ��ϴ�.\n");
					dataInfo.setValidCode("alpha");
				}
			}
			if(dataFormat.equals("phone")) {
				pattern = Pattern.compile("(\\d{2,3})-(\\d{3,4})-(\\d{4})");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] �׸��� ����ó ���Ŀ� ���� �ʽ��ϴ�.\n");
					dataInfo.setValidCode("phone");
				}
			}
		}
		resultMsg = resultMsgBuilder.toString();
		return isSuccess;
	}

	public String getResultMsg() {
		return resultMsg;
	}
}
