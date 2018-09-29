package com.cg.ems.dao;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.bean.EmployeeLeave;

public interface IAutoApprovalDao {

	List<EmployeeLeave> autoApprove();

}
