package org.corenel.services.domain;

import org.corenel.core.common.domain.ExcelVO;


@SuppressWarnings("serial")
public class ProductVO extends ExcelVO{

	private String productid;
	private String productnm;
	private String productkind;
	private String amount;
	private String useflag;
	private String enterdt;
	private String enteruser;
	private String modifydt;
	private String modifyuser;
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductnm() {
		return productnm;
	}
	public void setProductnm(String productnm) {
		this.productnm = productnm;
	}
	public String getProductkind() {
		return productkind;
	}
	public void setProductkind(String productkind) {
		this.productkind = productkind;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUseflag() {
		return useflag;
	}
	public void setUseflag(String useflag) {
		this.useflag = useflag;
	}
	public String getEnterdt() {
		return enterdt;
	}
	public void setEnterdt(String enterdt) {
		this.enterdt = enterdt;
	}
	public String getEnteruser() {
		return enteruser;
	}
	public void setEnteruser(String enteruser) {
		this.enteruser = enteruser;
	}
	public String getModifydt() {
		return modifydt;
	}
	public void setModifydt(String modifydt) {
		this.modifydt = modifydt;
	}
	public String getModifyuser() {
		return modifyuser;
	}
	public void setModifyuser(String modifyuser) {
		this.modifyuser = modifyuser;
	}
}
