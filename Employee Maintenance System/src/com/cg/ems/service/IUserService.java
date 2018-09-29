package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;

public interface IUserService {

	List<Employee> searchById(String empId, char wildcardChar);
	
	List<Employee> searchByFirstName(String empFName, char wildcardChar);
	
	List<Employee> searchByLastName(String empLName, char wildcardChar);
	
	List<Employee> searchByDept(List<String> empDeptNames, char wildcardChar);
	
	List<Employee> searchByGrade(List<String> empGrades, char wildcardChar);
	
	List<Employee> searchByMarital(List<Character> empMarital, char wildcardChar);
}
