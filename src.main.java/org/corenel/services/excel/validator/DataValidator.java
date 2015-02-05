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
		
		//상태유지를 하면 안되는 것들은 여기서 초기화한다.
		isSuccess = true;
		resultMsg = null;
		resultMsgBuilder.setLength(0);
		dataValue = dataInfo.getDataValue();
		dataDesc = dataInfo.getDataDesc();
		validityInfo = dataInfo.getValidityInfo();
		if(!dataInfo.isAcceptNull()) validityInfo.setRequired();  //Null허용이 아니면 필수 체크 활성화.
		dataLength = dataValue.getBytes().length;
		isEmpty = dataLength == 0 ? true : false;
		////////////////////////////////////////////////////////
		
		dataInfo.setValidCode("pass");
		//1. required
		if(isEmpty && validityInfo.isRequired()) {
			isSuccess = false;
//			resultMsgBuilder.append("[").append(dataDesc).append("] 항목은 필수 입력하셔야 합니다.\n");
			resultMsgBuilder.append("[").append(dataDesc).append("] 항목은 필수 입력하셔야 합니다.\n");
			dataInfo.setValidCode("required");
		}
		//2. matchedlength
		if(!isEmpty && validityInfo.getMachedLength() > 0 && dataLength != validityInfo.getMachedLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] 항목의 길이("+ dataLength +")가 지정길이("+ validityInfo.getMaxLength() +")와 맞지 않습니다.\n");
			dataInfo.setValidCode("machedLength");
		}
		//3. maxlength
		if(!isEmpty && validityInfo.getMaxLength() > 0 && dataLength > validityInfo.getMaxLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] 항목의 길이("+ dataLength +")가 최대길이("+ validityInfo.getMaxLength() +")를 초과하였습니다.\n");
			dataInfo.setValidCode("maxlength");
		}
		//4. minlength
		if(validityInfo.getMinLength() > 0 && dataLength < validityInfo.getMinLength()) {
			isSuccess = false;
			resultMsgBuilder.append("[").append(dataDesc).append("] 항목의 길이("+ dataLength +")가 최소길이("+ validityInfo.getMinLength() +")보다 작습니다.\n");
			dataInfo.setValidCode("minlength");
		}
		//5. compare
		if(validityInfo.getCompareGroup() != null && validityInfo.getCompareGroup().size() > 0) {
			if(validityInfo.getCompareGroup().get(dataValue) == null) {
				isSuccess = false;
				resultMsgBuilder.append("[").append(dataDesc).append("] 항목의 데이터가 구분값에 없습니다.\n");
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
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 이메일 형식에 맞지 않습니다.\n");
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
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 날짜 형식("+ validityInfo.getDateFormat() +")에 맞지 않습니다.\n");
					dataInfo.setValidCode("date");
				}
			}
			if(dataFormat.equals("number")) {
				pattern = Pattern.compile("^[0-9]*$");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 숫자 형식에 맞지 않습니다.\n");
					dataInfo.setValidCode("number");
				}
			}

			if(dataFormat.equals("double")) {
				pattern = Pattern.compile("\\d+\\.\\d+");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 숫자 형식에 맞지 않습니다.\n");
				}
			}
			if(dataFormat.equals("alpha")) {
				pattern = Pattern.compile("^[A-Za-z]*$");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 영문 형식에 맞지 않습니다.\n");
					dataInfo.setValidCode("alpha");
				}
			}
			if(dataFormat.equals("phone")) {
				pattern = Pattern.compile("(\\d{2,3})-(\\d{3,4})-(\\d{4})");
				if(!pattern.matcher(dataValue).matches()) {
					isSuccess = false;
					resultMsgBuilder.append("[").append(dataDesc).append("] 항목이 연락처 형식에 맞지 않습니다.\n");
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
