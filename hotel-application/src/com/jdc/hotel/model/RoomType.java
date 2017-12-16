package com.jdc.hotel.model;

import java.io.Serializable;

public class RoomType implements Serializable {

	private static final long serialVersionUID = 1L;

	public RoomType() {
	}

	private long id;

	private String name;

	private int bedCount;

	private double price;

	private Security security;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBedCount() {
		return bedCount;
	}

	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}