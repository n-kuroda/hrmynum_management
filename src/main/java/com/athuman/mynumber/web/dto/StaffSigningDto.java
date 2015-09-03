package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class StaffSigningDto implements Serializable {
	private String signingImage;

	public String getSigningImage() {
		return signingImage;
	}

	public void setSigningImage(String signingImage) {
		this.signingImage = signingImage;
	}
}
