package com.jdc.teacher.entity;

import com.jdc.teacher.entity.ClassRoom.Grade;

public class Student {

	private int id;
	private ClassRoom room;
	private String name;
	private String phone;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClassRoom getRoom() {
		return room;
	}

	public void setRoom(ClassRoom room) {
		this.room = room;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getYear() {
		return null == room ? null : room.getYear();
	}

	public Grade getGrade() {
		return null == room ? null : room.getGrade();
	}

	@Override
	public String toString() {
		return name;
	}
}
