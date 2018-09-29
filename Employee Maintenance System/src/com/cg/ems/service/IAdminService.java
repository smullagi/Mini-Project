package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;

public interface IAdminService {

	boolean addEmployee(Employee employee);
	
	boolean updateEmployee(Employee employee);
	
	List<Employee> getAllEmployee();
}
