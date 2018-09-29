package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.dao.IUserDao;
import com.cg.ems.dao.UserDaoImpl;

public class UserService implements IUserService {

	IUserDao userDao;
	
	public UserService() {
		userDao = new UserDaoImpl();
	}

	@Override
	public List<Employee> searchById(String empId, char wildcardChar) {
		return userDao.searchById(empId, wildcardChar);
	}

	@Override
	public List<Employee> searchByFirstName(String empFName, char wildcardChar) {
		return userDao.searchByFirstName(empFName, wildcardChar);
	}

	@Override
	public List<Employee> searchByLastName(String empLName, char wildcardChar) {
		return userDao.searchByLastName(empLName, wildcardChar);
	}

	@Override
	public List<Employee> searchByDept(List<String> empDeptNames, char wildcardChar) {
		return userDao.searchByDept(empDeptNames, wildcardChar);
	}

	@Override
	public List<Employee> searchByGrade(List<String> empGrades, char wildcardChar) {
		return userDao.searchByGrade(empGrades, wildcardChar);
	}

	@Override
	public List<Employee> searchByMarital(List<Character> empMarital, char wildcardChar) {
		return userDao.searchByMarital(empMarital, wildcardChar);
	}

}
