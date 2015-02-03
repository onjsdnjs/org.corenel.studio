package org.corenel.services.excel.validator;

public class DataInfo implements Cloneable {
	private int dataId=-1;				//DB에서 컬럼시퀀스;
	private String dataName=null;	//DB에서 컬럼명
	private String dataType = null;	//DB에서 컬럼타입
	private String dataValue=null;
	private String dataDesc=null;
	private boolean acceptNull = true;  //NULL허용 여부.
	private String defaultValue = null;
	private ValidityInfo validityInfo = null;	//데이터 값에 대한 유효성 체크 정보
	private boolean isTrimValue=true;
	private String validCode;
	
	public String getDataName() {
		return dataName;
	}

	public DataInfo setDataName(String dataName) {
		this.dataName = dataName;
		return this;
	}

	public String getDataDesc() {
		return dataDesc;
	}

	public DataInfo setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
		return this;
	}

	public ValidityInfo getValidityInfo() {
		return validityInfo;
	}

	public DataInfo setValidityInfo(ValidityInfo validityInfo) {
		this.validityInfo = validityInfo;
		validityInfo.valifyCondition();
		return this;
	}

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) throws Exception {
		if(isTrimValue) {
			dataValue = dataValue.trim();
		} 
		this.dataValue = dataValue;
	}

	public boolean isTrimValue() {
		return isTrimValue;
	}

	public void setTrimValue(boolean isTrimValue) {
		this.isTrimValue = isTrimValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isAcceptNull() {
		return acceptNull;
	}

	public void setAcceptNull(boolean acceptNull) {
		this.acceptNull = acceptNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	
	@Override
	public String toString() {
		return "DataInfo [validCode=" + validCode + "]";
	}

	@Override
	protected DataInfo clone() throws CloneNotSupportedException {
		return (DataInfo)super.clone();
	}
}
