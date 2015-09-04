package com.athuman.mynumber.web.dto;

import java.io.Serializable;
import java.util.List;

public class TACTRegistConfirmDto implements Serializable {

	private static final long serialVersionUID = -5845862294885188427L;
	private String no;
	private String staffNo;
	private List<Dependents> listDependent;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public List<Dependents> getListDependent() {
		return listDependent;
	}

	public void setListDependent(List<Dependents> listDependent) {
		this.listDependent = listDependent;
	}

}
