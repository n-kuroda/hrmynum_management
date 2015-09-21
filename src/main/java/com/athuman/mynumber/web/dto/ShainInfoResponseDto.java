package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class ShainInfoResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String resultMessage;

	private ShainInfoDto shainInfoDto;

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
