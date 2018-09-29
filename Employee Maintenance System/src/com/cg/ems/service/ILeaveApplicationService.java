package com.cg.ems.service;

import java.sql.Date;
import java.util.List;

import com.cg.ems.bean.EmployeeLeave;

public interface ILeaveApplicationService {

	boolean applyLeave(String empId, int leaveDuration, Date fromDate);
	
	List<EmployeeLeave> manageLeave(String empId);
}
