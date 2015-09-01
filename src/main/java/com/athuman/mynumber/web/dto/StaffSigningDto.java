package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class StaffSigningDto implements Serializable {

	private static final long serialVersionUID = 6111827109205631333L;

	private String signingImage;

	public String getSigningImage() {
		return signingImage;
	}

	public void setSigningImage(String signingImage) {
		this.signingImage = signingImage;
	}
}
