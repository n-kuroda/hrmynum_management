package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class StaffInfoDto implements Serializable {

	private static final long serialVersionUID = -1854357305260610275L;
	
	private String staffNo;

	private String nameSei;

	private String nameMei;

	private String nameKanaSei;

	private String nameKanaMei;
	
	private String token;

	public String getNameSei() {
		return nameSei;
	}

	public void setNameSei(String nameSei) {
		this.nameSei = nameSei;
	}

	public String getNameMei() {
		return nameMei;
	}

	public void setNameMei(String nameMei) {
		this.nameMei = nameMei;
	}

	public String getNameKanaSei() {
		return nameKanaSei;
	}

	public void setNameKanaSei(String nameKanaSei) {
		this.nameKanaSei = nameKanaSei;
	}

	public String getNameKanaMei() {
		return nameKanaMei;
	}

	public void setNameKanaMei(String nameKanaMei) {
		this.nameKanaMei = nameKanaMei;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getStaffNo() {
		return staffNo;
	}
	
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	
}
