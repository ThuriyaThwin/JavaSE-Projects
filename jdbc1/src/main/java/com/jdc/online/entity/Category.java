package com.jdc.online.entity;

import com.jdc.online.model.api.BaseEntity;
import com.jdc.online.model.api.Id;
import com.jdc.online.model.api.Table;

@Table("category")
public class Category implements BaseEntity {
	@Id
	private int id;
	private String name;

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
}
