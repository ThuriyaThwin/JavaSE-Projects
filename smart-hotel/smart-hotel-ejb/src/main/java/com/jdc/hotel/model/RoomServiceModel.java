package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.RoomService;

@LocalBean
@Stateless
public class RoomServiceModel extends AbstractModel<RoomService> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<RoomService> getType() {
		return RoomService.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
