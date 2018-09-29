package com.cg.ems.service;

import java.sql.Date;
import java.util.List;

import com.cg.ems.bean.EmployeeLeave;
import com.cg.ems.dao.ILeaveApplicationDao;
import com.cg.ems.dao.LeaveApplicationDaoImpl;

public class LeaveApplicationServiceImpl implements ILeaveApplicationService {

	ILeaveApplicationDao leaveApplicationDao;
	
	public LeaveApplicationServiceImpl() {
		leaveApplicationDao = new LeaveApplicationDaoImpl();
	}

	@Override
	public boolean applyLeave(String empId, int leaveDuration, Date fromDate) {
		return leaveApplicationDao.applyLeave(empId, leaveDuration, fromDate);
	}

	@Override
	public List<EmployeeLeave> manageLeave(String empId) {
		return leaveApplicationDao.manageLeave(empId);
	}

}
