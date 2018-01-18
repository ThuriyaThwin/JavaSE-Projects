package com.jdc.se.shop.service.vo;

import com.jdc.online.entity.Order;

public class OrderVO {

	private Order order;
	private CustomerVO customer;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}

}
