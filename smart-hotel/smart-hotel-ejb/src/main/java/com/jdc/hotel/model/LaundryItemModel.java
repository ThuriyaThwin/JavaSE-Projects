package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.LaundryItem;

@LocalBean
@Stateless
public class LaundryItemModel extends AbstractModel<LaundryItem> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<LaundryItem> getType() {
		return LaundryItem.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
