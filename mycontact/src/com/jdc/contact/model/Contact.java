package com.jdc.contact.model;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact>{

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Group group;
	
	public Contact() {
		System.out.println("Object is created");
	}
	
	public Contact(String [] array) {
		id = Integer.parseInt(array[0]);
		name = array[1];
		phone = array[2];
		email = array[3];
		address = array[4];
		group = Group.valueOf(array[5]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append("\t");
		sb.append(name).append("\t");
		sb.append(phone).append("\t");
		sb.append(email).append("\t");
		sb.append(address).append("\t");
		sb.append(group.toString());
		return sb.toString();
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

	@Override
	public int compareTo(Contact o) {
		return o.id - id;
	}
}
