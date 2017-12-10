package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.Customer;

@LocalBean
@Stateless
public class CustomerModel extends AbstractModel<Customer> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<Customer> getType() {
		return Customer.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
