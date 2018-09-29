package com.cg.lms.dao;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.User;

public interface IUserDao {

	User getUserByName(String userName) throws LMSException;
}
