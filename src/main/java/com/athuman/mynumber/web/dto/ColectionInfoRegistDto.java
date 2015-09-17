package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class ColectionInfoRegistDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String miteikyoRiyu1;

	private String miteikyoRiyu2;

	private String miteikyoRiyu3;

	private String miteikyoRiyu4;

	private byte[] staffSign;

	public byte[] getStaffSign() {
		return staffSign;
	}

	public void setStaffSign(byte[] staffSign) {
		this.staffSign = staffSign;
	}

	public String getMiteikyoRiyu1() {
		return miteikyoRiyu1;
	}

	public void setMiteikyoRiyu1(String miteikyoRiyu1) {
		this.miteikyoRiyu1 = miteikyoRiyu1;
	}

	public String getMiteikyoRiyu2() {
		return miteikyoRiyu2;
	}

	public void setMiteikyoRiyu2(String miteikyoRiyu2) {
		this.miteikyoRiyu2 = miteikyoRiyu2;
	}

	public String getMiteikyoRiyu3() {
		return miteikyoRiyu3;
	}

	public void setMiteikyoRiyu3(String miteikyoRiyu3) {
		this.miteikyoRiyu3 = miteikyoRiyu3;
	}

	public String getMiteikyoRiyu4() {
		return miteikyoRiyu4;
	}

	public void setMiteikyoRiyu4(String miteikyoRiyu4) {
		this.miteikyoRiyu4 = miteikyoRiyu4;
	}

}
