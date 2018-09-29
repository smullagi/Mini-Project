package com.cg.lms.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.lms.exception.LMSException;
import com.cg.lms.service.UserServiceImpl;
import com.cg.lms.util.ConnectionProvider;

public class TestUserServiceImpl {
	@Before
	public void setUp() throws Exception {
		Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO lmsUsers values(?,?,?)");
		st.setString(1,"dummyUser");
		st.setString(2, "p@ssw0rd");
		st.setString(3, "dummyRole");
		st.executeQuery();
		if(con!=null && !con.isClosed())
			con.close();
	}

	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM lmsUsers WHERE unm=?");
		st.setString(1,"dummyUser");
		st.executeQuery();
		if(con!=null && !con.isClosed())
			con.close();
	}

	@Test
	public void testGetRole() {
		UserServiceImpl userService = new UserServiceImpl();
		try {
			String role = userService.getRole("dummyUser","p@ssw0rd");
			assertEquals("Role mismatched", role, "dummyRole");
		} catch (LMSException e) {
			fail(e.getMessage());
		}		
		try {
			String role = userService.getRole("dummyUser123","password");
			fail("An exception has to be raised because given username wont match");
		} catch (LMSException e) {
			assertEquals("Expecting a different exception message", e.getMessage(), "No Such UserName");
		}		
		try {
			String role = userService.getRole("dummyUser","password");
			fail("An exception has to be raised because given password wont match");
		} catch (LMSException e) {
			assertEquals("Expecting a different exception message", e.getMessage(), "Password Mismatch");
		}		
	}

}
