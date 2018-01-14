package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.Customer;

public class CustomerRepo extends AbstractRepo<Customer> {

	public CustomerRepo() {
		super(Customer.class);
	}
}
