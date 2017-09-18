package com.jdc.ishop.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Category {

	private int id;

	private String name;

	private boolean delete;

	private LocalDateTime creation;

	private String createUser;

	private String createUserName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public LocalDateTime getCreation() {
		return creation;
	}

	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	public String getStatus() {
		return delete ? "Delete" : "Valid";
	}
	
	public String getCreationStr() {
		return creation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@Override
	public String toString() {
		return name;
	}
}