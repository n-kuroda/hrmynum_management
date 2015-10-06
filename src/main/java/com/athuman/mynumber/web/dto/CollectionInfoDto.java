package com.athuman.mynumber.web.dto;

import java.io.Serializable;
import java.util.List;

public class CollectionInfoDto implements Serializable {

	private static final long serialVersionUID = -5845862294885188427L;

	private String himodukeNo;

	private String staffNo;

	private String shodakuFlag;

	private List<Dependents> fuyoInfoList;

	public String getHimodukeNo() {
		return himodukeNo;
	}

	public void setHimodukeNo(String himodukeNo) {
		this.himodukeNo = himodukeNo;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getShodakuFlag() {
		return shodakuFlag;
	}

	public void setShodakuFlag(String shodakuFlag) {
		this.shodakuFlag = shodakuFlag;
	}

	public List<Dependents> getFuyoInfoList() {
		return fuyoInfoList;
	}

	public void setFuyoInfoList(List<Dependents> fuyoInfoList) {
		this.fuyoInfoList = fuyoInfoList;
	}

}
