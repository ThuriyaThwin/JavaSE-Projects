package com.jdc.hotel.model;

import java.io.Serializable;

public class LaundryItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public LaundryItem() {
	}

	private long id;

	private String name;

	private double unitPrice;

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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}