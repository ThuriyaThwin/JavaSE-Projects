package com.jdc.hotel.model;

import java.time.format.DateTimeFormatter;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class AbstractSecurityModel {

	private Security security;
	
	public AbstractSecurityModel() {
		security = new Security();
	}
	
	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	@JsonIgnore
	public String getStatus() {
		return security.isDelFlag() ? "Delete" : "Valid";
	}

	@JsonIgnore
	public String getCreation() {
		return security.getCreation() == null ? ""
				: security.getCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@JsonIgnore
	public String getUpdateTime() {
		return security.getModification() == null ? ""
				: security.getModification().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@JsonIgnore
	public String getCreateUser() {
		return security.getCreateUser();
	}

	@JsonIgnore
	public String getUpdateUser() {
		return security.getCreateUser();
	}
}
