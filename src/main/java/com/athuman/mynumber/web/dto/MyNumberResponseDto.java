package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class MyNumberResponseDto implements Serializable {

	private static final long serialVersionUID = 3609852402007880073L;
	
	private String resultMessage;
	
	private String myNumber;

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
