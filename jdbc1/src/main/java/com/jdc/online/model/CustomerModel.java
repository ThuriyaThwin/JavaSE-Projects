package com.jdc.online.model;

import com.jdc.jdbc.helper.AbstractModel;
import com.jdc.online.entity.Customer;

public class CustomerModel extends AbstractModel<Customer> {

	public CustomerModel() {
		super(Customer.class);
	}
}
