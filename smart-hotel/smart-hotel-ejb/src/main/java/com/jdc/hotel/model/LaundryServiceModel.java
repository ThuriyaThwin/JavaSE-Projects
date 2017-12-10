package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.LaundryService;

@LocalBean
@Stateless
public class LaundryServiceModel extends AbstractModel<LaundryService> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<LaundryService> getType() {
		return LaundryService.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
