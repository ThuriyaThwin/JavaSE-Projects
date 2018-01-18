package com.jdc.se.shop.service.vo;

import com.jdc.online.entity.Customer;
import com.jdc.online.entity.State;
import com.jdc.online.entity.Township;

public class CustomerVO {

	private Customer customer;
	private Township township;
	private State state;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
