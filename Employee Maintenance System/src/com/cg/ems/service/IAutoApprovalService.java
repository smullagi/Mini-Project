package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.bean.EmployeeLeave;

public interface IAutoApprovalService {

	List<EmployeeLeave> autoApprove();
}
