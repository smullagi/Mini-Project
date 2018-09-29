package com.cg.ems.dao;

import java.sql.Date;
import java.util.List;

import com.cg.ems.bean.EmployeeLeave;

public class LeaveApplicationDaoImpl implements ILeaveApplicationDao {

	@Override
	public boolean applyLeave(String empId, int leaveDuration, Date fromDate) {
		return false;
	}

	@Override
	public List<EmployeeLeave> manageLeave(String empId) {
		return null;
	}

}
