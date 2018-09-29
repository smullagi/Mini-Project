package com.cg.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.User;
import com.cg.lms.util.ConnectionProvider;

public class UserDaoImpl implements IUserDao{

	private Logger log = Logger.getLogger("UserDAO");
	
	@Override
	public User getUserByName(String userName) throws LMSException {
		User user = null;
		try(
				Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con.prepareStatement(IQueryMapper.GET_USER)
			){
			
			st.setString(1, userName);
			
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setRole(rs.getString(3));
			}
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To FEtch Records");
		}
		return user;
	}

	
}
