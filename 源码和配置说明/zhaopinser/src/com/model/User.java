package com.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User {

	// Fields

	private Integer userId;
	private String userName;
	private String userPassword;
	private String userXingming;
	private String userAge;
	private String userSex;
	private Integer roleId;

	// Constructors

	/** default constructor */
	public User() {
		super();
	}
	
	/** bufen constructor */
	public User(String userName, String userPassword, String userXingming, String userAge, String userSex,
			Integer roleId) {
		super();
		this.userPassword = userPassword;
		this.userName = userName;
		this.userXingming = userXingming;
		this.userAge = userAge;
		this.userSex = userSex;
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
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

	public String getUserXingming() {
		return userXingming;
	}

	public void setUserXingming(String userXingming) {
		this.userXingming = userXingming;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	

}