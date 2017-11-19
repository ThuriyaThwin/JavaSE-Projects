package com.jdc.contact.model;

public class Contact {

	private int id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Group group;
	
	private static int COUNTER;
	
	public Contact() {
		id = ++COUNTER;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public enum Group {
		School, Work, Friend, Others
	}
}
