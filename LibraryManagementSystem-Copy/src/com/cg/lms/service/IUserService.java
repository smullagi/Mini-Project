package com.cg.lms.service;

import com.cg.lms.exception.LMSException;

public interface IUserService {
	String getRole(String unm,String pwd) throws LMSException;
}
