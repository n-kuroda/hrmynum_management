package com.athuman.mynumber.web.dto;

import org.springframework.context.annotation.Scope;
@Scope("session")
public class StaffSelection {

	private String staffNo;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}


}
