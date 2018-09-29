package com.cg.ems.bean;
//Bean class for User
public class User {

	private String userId;
	private String userName;
	private String userPassword;
	private String userType;
	
	
	//Default constructor for User
		public User() {
			super();
			
		}
		//Parameterized constructor for User
	public User(String userId, String userName, String userPassword,
			String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userType = userType;
	}
	
	//Getters and Setters for User properties
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	//toString method for User
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userType=" + userType
				+ "]";
	}
	
}
