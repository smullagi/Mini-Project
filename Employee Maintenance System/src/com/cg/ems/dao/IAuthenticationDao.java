package com.cg.ems.dao;

public interface IAuthenticationDao {

	boolean loginSuccessful(String userName, String userPassword);
}
