package org.corenel.services.excel.validator;

import java.util.Map;

public class ValidityInfo implements Cloneable {
	
	private boolean isRequired=false;
	private int minLength=0;
	private int maxLength=0;
	private int machedLength=0;
	private Class<? extends Number> numberType=null;
	private Map<String,String> compareGroup=null;
	private String dataFormat=null;
	private String dateFormat=null;
	
	public boolean valifyCondition() {
		if(maxLength==0) {
			//log.debug("");
		}
		return true;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public ValidityInfo setRequired(boolean isRequired) {
		this.isRequired = isRequired;
		return this;
	}
	
	public ValidityInfo setRequired() {
		this.isRequired = true;
		return this;
	}

	public int getMinLength() {
		return minLength;
	}

	public ValidityInfo setMinLength(int minLength) {
		this.minLength = minLength;
		return this;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public ValidityInfo setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		return this;
	}
	
	public ValidityInfo setMaxLength(Class<? extends Number> numberType) {
		this.numberType = numberType;
		return this;
	}
	
	public ValidityInfo setMachedLength(int machedLength) {
		this.machedLength = machedLength;
		return this;
	}

	public Map<String,String> getCompareGroup() {
		return compareGroup;
	}

	public ValidityInfo setCompareGroup(Map<String,String> compareGroup) {
		this.compareGroup = compareGroup;
		return this;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public ValidityInfo setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
		return this;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public ValidityInfo setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
		return this;
	}

	public Class<? extends Number> getNumberType() {
		return numberType;
	}

	public void setNumberType(Class<? extends Number> numberType) {
		this.numberType = numberType;
	}

	public int getMachedLength() {
		return machedLength;
	}
	
	@Override
	protected ValidityInfo clone() throws CloneNotSupportedException {
		return (ValidityInfo)super.clone();
	}
	
	
}
