package com.jdc.online.entity;

import com.jdc.jdbc.helper.BaseEntity;
import com.jdc.jdbc.helper.Id;

public class Product implements BaseEntity{

	@Id
	private int id;
	private String name;
	private int price;
	private int category_id;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

}
