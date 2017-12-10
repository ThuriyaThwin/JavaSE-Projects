package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.Room;

@LocalBean
@Stateless
public class RoomModel extends AbstractModel<Room> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<Room> getType() {
		return Room.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
