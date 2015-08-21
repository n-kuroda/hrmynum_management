package cresco.co.jp.mynumber.model;

import org.hibernate.validator.constraints.Range;

public class MyNumberRegistration {

	@Range(min=100000, max=999999)
	private int employeeId;

	private String employeeName;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
