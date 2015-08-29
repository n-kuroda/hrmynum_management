package com.athuman.mynumber.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class MyNumberRegistDto implements Serializable {

	private static final long serialVersionUID = -2743699340471293639L;

	@NotEmpty
	@Size(min=12,max=12)
	@Pattern(regexp = "^[0-9]*$")
	private String myNumber;

	@NotEmpty
	private String myNumberConfirm;

	private String driversLicense;

	private String driveHistoryLicense;

	private String passPort;

	private String bodyDisabilitiesNotebook;

	private String mentalDisabilitiesNotebook;

	private String rehabilitationNotebook;

	private String stayCard;

	private String clearPerson;

	private String healthInsuranceLicense;

	private String pensionNotebook;

	private String other;

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getMyNumberConfirm() {
		return myNumberConfirm;
	}

	public void setMyNumberConfirm(String myNumberConfirm) {
		this.myNumberConfirm = myNumberConfirm;
	}

	public String getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}

	public String getDriveHistoryLicense() {
		return driveHistoryLicense;
	}

	public void setDriveHistoryLicense(String driveHistoryLicense) {
		this.driveHistoryLicense = driveHistoryLicense;
	}

	public String getPassPort() {
		return passPort;
	}

	public void setPassPort(String passPort) {
		this.passPort = passPort;
	}

	public String getBodyDisabilitiesNotebook() {
		return bodyDisabilitiesNotebook;
	}

	public void setBodyDisabilitiesNotebook(String bodyDisabilitiesNotebook) {
		this.bodyDisabilitiesNotebook = bodyDisabilitiesNotebook;
	}

	public String getMentalDisabilitiesNotebook() {
		return mentalDisabilitiesNotebook;
	}

	public void setMentalDisabilitiesNotebook(String mentalDisabilitiesNotebook) {
		this.mentalDisabilitiesNotebook = mentalDisabilitiesNotebook;
	}

	public String getRehabilitationNotebook() {
		return rehabilitationNotebook;
	}

	public void setRehabilitationNotebook(String rehabilitationNotebook) {
		this.rehabilitationNotebook = rehabilitationNotebook;
	}

	public String getStayCard() {
		return stayCard;
	}

	public void setStayCard(String stayCard) {
		this.stayCard = stayCard;
	}

	public String getClearPerson() {
		return clearPerson;
	}

	public void setClearPerson(String clearPerson) {
		this.clearPerson = clearPerson;
	}

	public String getHealthInsuranceLicense() {
		return healthInsuranceLicense;
	}

	public void setHealthInsuranceLicense(String healthInsuranceLicense) {
		this.healthInsuranceLicense = healthInsuranceLicense;
	}

	public String getPensionNotebook() {
		return pensionNotebook;
	}

	public void setPensionNotebook(String pensionNotebook) {
		this.pensionNotebook = pensionNotebook;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
