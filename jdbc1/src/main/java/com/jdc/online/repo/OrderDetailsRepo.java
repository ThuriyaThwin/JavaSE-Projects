package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.OrderDetails;

public class OrderDetailsRepo extends AbstractRepo<OrderDetails> {

	public OrderDetailsRepo() {
		super(OrderDetails.class);
	}

}
