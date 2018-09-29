package com.cg.ems.dao;

import java.sql.Date;
import java.util.List;

import com.cg.ems.bean.EmployeeLeave;

public interface ILeaveApplicationDao {

	boolean applyLeave(String empId, int leaveDuration, Date fromDate);

	List<EmployeeLeave> manageLeave(String empId);

}
