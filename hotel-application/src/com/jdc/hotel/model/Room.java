package com.jdc.hotel.model;

import java.io.Serializable;

public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	public Room() {
	}

	private long id;

	private String roomNumber;

	private RoomType type;

	private Security security;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}