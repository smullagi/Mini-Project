package com.cg.lms.service;

import com.cg.lms.dao.IUserDao;
import com.cg.lms.dao.UserDaoImpl;
import com.cg.lms.exception.LMSException;
import com.cg.lms.model.User;

public class UserServiceImpl implements IUserService{

	private IUserDao dao = new UserDaoImpl();
	
	@Override
	public String getRole(String unm, String pwd) throws LMSException {
		String role=null;
		User user = dao.getUserByName(unm);
		if(user==null)
			throw new LMSException("No Such UserName");
		else if(!pwd.equals(user.getPassword()))
			throw new LMSException("Password Mismatch");
		else
			role=user.getRole();
		return role;
	}

}
