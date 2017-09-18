package com.jdc.ishop.model.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {

	private int id;

	private int categoryId;

	private String name;

	private String barcode;

	private int price;

	private boolean delete;

	private LocalDateTime creation;
	private String createUser;
	
	private String categoryName;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getStatus() {
		return delete ? "Delete" : "Valid";
	}
	
	public String getCreationStr() {
		return creation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

}