package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class ShainInfoResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int httpStatus;

	private String resultMessage;

	private ShainInfoDto shainInfoDto;

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public ShainInfoDto getShainInfoDto() {
		return shainInfoDto;
	}

	public void setShainInfoDto(ShainInfoDto shainInfoDto) {
		this.shainInfoDto = shainInfoDto;
	}

}
