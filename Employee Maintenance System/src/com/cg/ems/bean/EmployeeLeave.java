package com.cg.ems.bean;

import java.sql.Date;

//Bean class for EmployeeLeave

public class EmployeeLeave {

	private String empId;
	private int leaveId;
	private int leaveBalance;
	private int leaveDuration;
	private Date fromDate;
	private Date toDate;
	private String status;
	
	//Default constructor for EmployeeLeave
	public EmployeeLeave() {
		super();
		
	}
	//Parameterized constructor for EmployeeLeave
	public EmployeeLeave(String empId, int leaveId, int leaveBalance,
			int leaveDuration, Date fromDate, Date toDate, String status) {
		super();
		this.empId = empId;
		this.leaveId = leaveId;
		this.leaveBalance = leaveBalance;
		this.leaveDuration = leaveDuration;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
	}
	//Getters and Setters for EmployeeLeave properties
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public int getLeaveDuration() {
		return leaveDuration;
	}
	public void setLeaveDuration(int leaveDuration) {
		this.leaveDuration = leaveDuration;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//toString method for EmployeeLeave
	@Override
	public String toString() {
		return "EmployeeLeave [empId=" + empId + ", leaveId=" + leaveId
				+ ", leaveBalance=" + leaveBalance + ", leaveDuration="
				+ leaveDuration + ", fromDate=" + fromDate + ", toDate="
				+ toDate + ", status=" + status + "]";
	}
	
	
}
