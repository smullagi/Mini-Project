package com.cg.ems.dao;

import java.util.List;

import com.cg.ems.bean.Employee;

public class UserDaoImpl implements IUserDao {

	@Override
	public List<Employee> searchById(String empId, char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByFirstName(String empFName, char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByLastName(String empLName, char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByDept(List<String> empDeptNames,
			char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByGrade(List<String> empGrades,
			char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> searchByMarital(List<Character> empMarital,
			char wildcardChar) {
		// TODO Auto-generated method stub
		return null;
	}

}
