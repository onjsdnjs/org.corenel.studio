package org.corenel.core.common.domain;

import java.io.Serializable;
import java.util.List;

import org.corenel.core.message.CommandMessage;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@SuppressWarnings("serial")
public class ExcelVO extends CommandMessage implements Serializable{
	
	private CommonsMultipartFile file;
	private String filename;
	private List<ExcelVO> dataList;
	private List<String> columnTitle;
	private List<String> columnList;
	private List<String> columnType;
	private int count;
	private int endColumIndex;
	private int startColumIndex;
	private int startRowIndex;
	private String state;
	private String mid;

	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<ExcelVO> getDataList() {
		return dataList;
	}

	public void setDataList(List<ExcelVO> dataList) {
		this.dataList = dataList;
	}

	public List<String> getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(List<String> columnTitle) {
		this.columnTitle = columnTitle;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	public List<String> getColumnType() {
		return columnType;
	}

	public void setColumnType(List<String> columnType) {
		this.columnType = columnType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getEndColumIndex() {
		return endColumIndex;
	}
	public void setEndColumIndex(int endColumIndex) {
		this.endColumIndex = endColumIndex;
	}
	public int getStartColumIndex() {
		return startColumIndex;
	}
	public void setStartColumIndex(int startColumIndex) {
		this.startColumIndex = startColumIndex;
	}
	public int getStartRowIndex() {
		return startRowIndex;
	}
	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
}
