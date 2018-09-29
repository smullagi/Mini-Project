package com.cg.ems.dao;

import java.util.List;

import com.cg.ems.bean.Employee;

public interface IAdminDao {

		boolean addEmployee(Employee employee);
		
		boolean updateEmployee(Employee employee);
		
		List<Employee> getAllEmployee();
}
