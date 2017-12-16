package com.jdc.hotel.model;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	public Account() {
	}

	private String loginId;

	private String password;

	private String name;

	private Role role;

	private Security security;

	public enum Role {
		Manager, Front, Admin, Other
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}