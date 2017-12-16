package com.jdc.hotel.model;

import java.io.Serializable;

public class RoomServiceItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public RoomServiceItem() {
	}

	private long id;

	private String name;

	private String unitPrice;

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

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}