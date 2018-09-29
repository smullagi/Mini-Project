package com.cg.ems.service;

import com.cg.ems.dao.AuthenticationDaoImpl;
import com.cg.ems.dao.IAuthenticationDao;

public class AuthenticationServiceImpl implements IAuthenticationService {

	IAuthenticationDao authenticationDao;
	
	public AuthenticationServiceImpl() {
		authenticationDao = new AuthenticationDaoImpl();
	}

	public boolean loginSuccessful(String userName, String userPassword) {
		return authenticationDao.loginSuccessful(userName, userPassword);
	}
}
