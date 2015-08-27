package com.athuman.mynumber.web.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
@Scope("session")
public class MyNumberRegistDto {

	@NotEmpty
	@Size(min=12,max=12)
	@Pattern(regexp = "^[0-9]*$")
	private String myNumber;

	@NotEmpty
	private String cardInfo;

	private String driverLicense;

	private String drivingExperience;

	private String passport;

	private String healthInsurance;

	private String insuranceHandbook;

	private String manualCare;

	private String stayCard;

	private String insuranceCard;

	private String pensionBook;

	private String other;

	public String getMyNumber() {
		return myNumber;
	}

	public void setMyNumber(String myNumber) {
		this.myNumber = myNumber;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getDrivingExperience() {
		return drivingExperience;
	}

	public void setDrivingExperience(String drivingExperience) {
		this.drivingExperience = drivingExperience;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getHealthInsurance() {
		return healthInsurance;
	}

	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}

	public String getInsuranceHandbook() {
		return insuranceHandbook;
	}

	public void setInsuranceHandbook(String insuranceHandbook) {
		this.insuranceHandbook = insuranceHandbook;
	}

	public String getManualCare() {
		return manualCare;
	}

	public void setManualCare(String manualCare) {
		this.manualCare = manualCare;
	}

	public String getStayCard() {
		return stayCard;
	}

	public void setStayCard(String stayCard) {
		this.stayCard = stayCard;
	}

	public String getInsuranceCard() {
		return insuranceCard;
	}

	public void setInsuranceCard(String insuranceCard) {
		this.insuranceCard = insuranceCard;
	}

	public String getPensionBook() {
		return pensionBook;
	}

	public void setPensionBook(String pensionBook) {
		this.pensionBook = pensionBook;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}
}
