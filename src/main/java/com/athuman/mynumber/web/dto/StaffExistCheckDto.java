package com.athuman.mynumber.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;

@Scope("session")
public class StaffExistCheckDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -957193794743603714L;

	@NotEmpty
	@Size(min=9,max=9)
	@Pattern(regexp = "^[0-9]*$")
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
