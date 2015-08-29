package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class TACTMyNumberResponseDto implements Serializable {

	private static final long serialVersionUID = 3609852402007880073L;
	
	private int httpStatus;
	
	private String resultMessage;
	
	private String myNumber;

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

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

}
