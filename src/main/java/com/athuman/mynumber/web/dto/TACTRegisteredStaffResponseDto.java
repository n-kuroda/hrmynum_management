package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class TACTRegisteredStaffResponseDto implements Serializable {

	private static final long serialVersionUID = 3609852402007880073L;
	
	private int httpStatus;
	
	private String resultMessage;
	
	private String result;

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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
