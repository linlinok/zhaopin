package com.model;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleMark;

	// Constructors

	/** default constructor */
	public Role() {
		super();
	}

	/** full constructor */
	public Role(String roleName, String roleMark) {
		super();
		this.roleName = roleName;
		this.roleMark = roleMark;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMark() {
		return roleMark;
	}

	public void setRoleMark(String roleMark) {
		this.roleMark = roleMark;
	}
}