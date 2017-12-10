package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.RoomType;

@LocalBean
@Stateless
public class RoomTypeModel extends AbstractModel<RoomType> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<RoomType> getType() {
		return RoomType.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
