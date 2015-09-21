package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class StaffInfoResponseDto implements Serializable {

	private static final long serialVersionUID = -2865041300573218373L;

	private String resultMessage;

	private StaffInfoDto staffInfoDto;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public StaffInfoDto getStaffInfoDto() {
		return staffInfoDto;
	}

	public void setStaffInfoDto(StaffInfoDto staffInfoDto) {
		this.staffInfoDto = staffInfoDto;
	}

}
