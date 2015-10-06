package com.athuman.mynumber.web.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dependents implements Serializable {

	private static final long serialVersionUID = -470489021673919714L;

	private String fuyoNameSei;

	private String fuyoNameMei;
	
	private String fuyoSeinengapi;

	@JsonIgnore
	private String fuyoSeinengapiYear;

	@JsonIgnore
	private String fuyoSeinengapiMonth;

	@JsonIgnore
	private String fuyoSeinengapiDay;

	private String fuyoZokugara;

	private String fuyoZokugaraSonota;

	@JsonIgnore
	private String fuyoMyNumber;

	private String daisangoHihokensha;

	public String getFuyoNameSei() {
		return fuyoNameSei;
	}

	public void setFuyoNameSei(String fuyoNameSei) {
		this.fuyoNameSei = fuyoNameSei;
	}

	public String getFuyoNameMei() {
		return fuyoNameMei;
	}

	public void setFuyoNameMei(String fuyoNameMei) {
		this.fuyoNameMei = fuyoNameMei;
	}
	
	public String getFuyoSeinengapi() {
		return fuyoSeinengapi;
	}

	public void setFuyoSeinengapi(String fuyoSeinengapi) {
		this.fuyoSeinengapi = fuyoSeinengapi;
	}


	public String getFuyoSeinengapiYear() {
		return fuyoSeinengapiYear;
	}

	public void setFuyoSeinengapiYear(String fuyoSeinengapiYear) {
		this.fuyoSeinengapiYear = fuyoSeinengapiYear;
	}

	public String getFuyoSeinengapiMonth() {
		return fuyoSeinengapiMonth;
	}

	public void setFuyoSeinengapiMonth(String fuyoSeinengapiMonth) {
		this.fuyoSeinengapiMonth = fuyoSeinengapiMonth;
	}

	public String getFuyoSeinengapiDay() {
		return fuyoSeinengapiDay;
	}

	public void setFuyoSeinengapiDay(String fuyoSeinengapiDay) {
		this.fuyoSeinengapiDay = fuyoSeinengapiDay;
	}

	public String getFuyoZokugara() {
		return fuyoZokugara;
	}

	public void setFuyoZokugara(String fuyoZokugara) {
		this.fuyoZokugara = fuyoZokugara;
	}

	public String getFuyoZokugaraSonota() {
		return fuyoZokugaraSonota;
	}

	public void setFuyoZokugaraSonota(String fuyoZokugaraSonota) {
		this.fuyoZokugaraSonota = fuyoZokugaraSonota;
	}

	public String getFuyoMyNumber() {
		return fuyoMyNumber;
	}

	public void setFuyoMyNumber(String fuyoMyNumber) {
		this.fuyoMyNumber = fuyoMyNumber;
	}

	public String getDaisangoHihokensha() {
		return daisangoHihokensha;
	}

	public void setDaisangoHihokensha(String daisangoHihokensha) {
		this.daisangoHihokensha = daisangoHihokensha;
	}

}
