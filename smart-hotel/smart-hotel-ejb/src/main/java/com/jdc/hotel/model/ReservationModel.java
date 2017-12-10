package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.Reservation;

@LocalBean
@Stateless
public class ReservationModel extends AbstractModel<Reservation> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<Reservation> getType() {
		return Reservation.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
