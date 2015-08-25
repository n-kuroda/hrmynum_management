package com.athuman.mynumber.web.dto;

import org.springframework.context.annotation.Scope;
@Scope("session")
public class StaffExistCheckDto {

	private String staffNo;

	private String staffName;

	private String staffNameKana;

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffNameKana() {
		return staffNameKana;
	}

	public void setStaffNameKana(String staffNameKana) {
		this.staffNameKana = staffNameKana;
	}
}
