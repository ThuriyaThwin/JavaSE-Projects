package com.jdc.online.entity;

import com.jdc.online.model.api.BaseEntity;
import com.jdc.online.model.api.Column;
import com.jdc.online.model.api.Id;

public class Township implements BaseEntity{

	@Id
	private int id;
	private String name;
	@Column(name="state_id")
	private int stateId;

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

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

}
