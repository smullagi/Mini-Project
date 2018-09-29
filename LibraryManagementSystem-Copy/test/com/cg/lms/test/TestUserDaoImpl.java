package com.cg.lms.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.lms.dao.UserDaoImpl;
import com.cg.lms.exception.LMSException;
import com.cg.lms.model.User;
import com.cg.lms.util.ConnectionProvider;

public class TestUserDaoImpl {
		
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
	public void testGetUserByName() {
		try {
			User user = (new UserDaoImpl()).getUserByName("dummyUser");			
			assertNotNull("Existing User Not Retrived",user);
		} catch (LMSException e) {
			fail(e.getMessage());
		}
	}

}
