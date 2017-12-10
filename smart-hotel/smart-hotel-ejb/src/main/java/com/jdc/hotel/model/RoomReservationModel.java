package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.RoomReservation;

@LocalBean
@Stateless
public class RoomReservationModel extends AbstractModel<RoomReservation> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<RoomReservation> getType() {
		return RoomReservation.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
