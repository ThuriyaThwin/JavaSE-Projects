package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.Order;

public class OrderRepo extends AbstractRepo<Order> {

	public OrderRepo() {
		super(Order.class);
	}

}
