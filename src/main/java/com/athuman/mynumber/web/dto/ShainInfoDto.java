package com.athuman.mynumber.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ShainInfoDto implements Serializable {

	private static final long serialVersionUID = -957193794743603714L;

	@NotEmpty
	@Size(min = 6, max = 6)
	@Pattern(regexp = "^[0-9]*$")
	private String shainNo;
	
	private String shainNameSei;
	
	private String shainNameMei;
	
	private String shainNameSeiKana;
	
	private String shainNameMeiKana;

	public String getShainNo() {
		return shainNo;
	}

	public void setShainNo(String shainNo) {
		this.shainNo = shainNo;
	}

	public String getShainNameSei() {
		return shainNameSei;
	}

	public void setShainNameSei(String shainNameSei) {
		this.shainNameSei = shainNameSei;
	}

	public String getShainNameMei() {
		return shainNameMei;
	}

	public void setShainNameMei(String shainNameMei) {
		this.shainNameMei = shainNameMei;
	}

	public String getShainNameSeiKana() {
		return shainNameSeiKana;
	}

	public void setShainNameSeiKana(String shainNameSeiKana) {
		this.shainNameSeiKana = shainNameSeiKana;
	}

	public String getShainNameMeiKana() {
		return shainNameMeiKana;
	}

	public void setShainNameMeiKana(String shainNameMeiKana) {
		this.shainNameMeiKana = shainNameMeiKana;
	}
	
}

	