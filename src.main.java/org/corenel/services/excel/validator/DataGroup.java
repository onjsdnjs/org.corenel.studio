package org.corenel.services.excel.validator;

import java.util.Iterator;
import java.util.List;

public class DataGroup {
	private String groupName;	//���̺��
	private String accessName;	//����ID
	private long rows;
	private List<DataInfo> dataList;
	public enum LoadStatus {INIT, INFO, DATA};  //INIT : �ʱ����, INFO: DataInfo�� ��Ÿ���� ����, DATA: DataInfo�� �� ����.
	
	private DataGroup() {
		super();
	}
	
	public DataGroup(List<DataInfo> dataList) {
		super();
		this.dataList = dataList;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public long getRows() {
		return rows;
	}
	public void setRows(long rows) {
		this.rows = rows;
	}
	public void addData(DataInfo dataInfo) {
		this.dataList.add(dataInfo);
	}
	public DataInfo getData(int dataId) {
		Iterator<DataInfo> tempIt = this.dataList.iterator();
		DataInfo tempDi = null;
		while(tempIt.hasNext()) {
			tempDi = tempIt.next();
			if(tempDi.getDataId() == dataId) {
				break;
			}
		}
		return tempDi;
	}
	public DataInfo getData(String dataName) {
		Iterator<DataInfo> tempIt = this.dataList.iterator();
		DataInfo tempDi = null;
		while(tempIt.hasNext()) {
			tempDi = tempIt.next();
			if(tempDi.getDataName().equals(dataName)) {
				break;
			}
		}
		return tempDi;
	}
	public List<DataInfo> getDataList() {
		return dataList;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}
}
