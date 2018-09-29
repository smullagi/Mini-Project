package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.dao.AdminDaoImpl;
import com.cg.ems.dao.IAdminDao;

public class AdminServiceImpl implements IAdminService {

	IAdminDao adminDao;
	
	public AdminServiceImpl() {
		adminDao = new AdminDaoImpl();
	}

	@Override
	public boolean addEmployee(Employee employee) {
		return adminDao.addEmployee(employee);
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		return adminDao.updateEmployee(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return adminDao.getAllEmployee();
	}

}
