package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.Customer;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.CustomerModel;

@Path("Customer")
public class CustomerResource extends AbstractResource<Customer> {

	@Inject
	private CustomerModel model;

	@Override
	protected AbstractModel<Customer> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from Customer t ";
	}

}
