package com.jdc.online.entity;

import java.time.LocalDate;

import com.jdc.jdbc.helper.BaseEntity;
import com.jdc.jdbc.helper.Column;
import com.jdc.jdbc.helper.Id;

public class Customer implements BaseEntity {

	@Id
	private int id;
	private String name;
	private String address;
	private String email;
	private LocalDate dob;
	private String remark;
	@Column("township_id")
	private int townshipId;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(int townshipId) {
		this.townshipId = townshipId;
	}

}
