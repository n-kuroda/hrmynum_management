package com.athuman.mynumber.web.dto;

import java.io.Serializable;

public class Dependents implements Serializable {

	private static final long serialVersionUID = -470489021673919714L;

	private String dependentsNameSei;

	private String dependentsNameMei;

	private String dependentsBirthdayYear;

	private String dependentsBirthdayMonth;

	private String dependentsBirthdayDay;

	private String dependentsRelationship;

	private String dependentsRelationshipOther;

	private String dependentsMyNumber;

	public String getDependentsNameSei() {
		return dependentsNameSei;
	}

	public void setDependentsNameSei(String dependentsNameSei) {
		this.dependentsNameSei = dependentsNameSei;
	}

	public String getDependentsNameMei() {
		return dependentsNameMei;
	}

	public void setDependentsNameMei(String dependentsNameMei) {
		this.dependentsNameMei = dependentsNameMei;
	}

	public String getDependentsBirthdayYear() {
		return dependentsBirthdayYear;
	}

	public void setDependentsBirthdayYear(String dependentsBirthdayYear) {
		this.dependentsBirthdayYear = dependentsBirthdayYear;
	}

	public String getDependentsBirthdayMonth() {
		return dependentsBirthdayMonth;
	}

	public void setDependentsBirthdayMonth(String dependentsBirthdayMonth) {
		this.dependentsBirthdayMonth = dependentsBirthdayMonth;
	}

	public String getDependentsBirthdayDay() {
		return dependentsBirthdayDay;
	}

	public void setDependentsBirthdayDay(String dependentsBirthdayDay) {
		this.dependentsBirthdayDay = dependentsBirthdayDay;
	}

	public String getDependentsRelationship() {
		return dependentsRelationship;
	}

	public void setDependentsRelationship(String dependentsRelationship) {
		this.dependentsRelationship = dependentsRelationship;
	}

	public String getDependentsRelationshipOther() {
		return dependentsRelationshipOther;
	}

	public void setDependentsRelationshipOther(String dependentsRelationshipOther) {
		this.dependentsRelationshipOther = dependentsRelationshipOther;
	}

	public String getDependentsMyNumber() {
		return dependentsMyNumber;
	}

	public void setDependentsMyNumber(String dependentsMyNumber) {
		this.dependentsMyNumber = dependentsMyNumber;
	}

}
