package com.athuman.mynumber.web.dto;

import java.io.Serializable;
import java.util.List;

public class RegistConfirmDto implements Serializable {


	private static final long serialVersionUID = -2153869944157315811L;

	private String staffName;

	private String myNumber;

	private String myNumberConfirm;

	private String identification;

	private byte[] staffSignning;

	private List<Dependents> lstDependents;

	public byte[] getStaffSignning() {
		return staffSignning;
	}

	public void setStaffSignning(byte[] staffSignning) {
		this.staffSignning = staffSignning;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getMyNumberConfirm() {
		return myNumberConfirm;
	}

	public void setMyNumberConfirm(String myNumberConfirm) {
		this.myNumberConfirm = myNumberConfirm;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public List<Dependents> getLstDependents() {
		return lstDependents;
	}

	public void setLstDependents(List<Dependents> lstDependents) {
		this.lstDependents = lstDependents;
	}

}
