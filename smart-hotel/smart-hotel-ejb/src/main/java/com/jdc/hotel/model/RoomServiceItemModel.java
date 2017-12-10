package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.RoomServiceItem;

@LocalBean
@Stateless
public class RoomServiceItemModel extends AbstractModel<RoomServiceItem> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<RoomServiceItem> getType() {
		return RoomServiceItem.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
