package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.Reservation;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.ReservationModel;

@Path("Reservation")
public class ReservationResource extends AbstractResource<Reservation> {

	@Inject
	private ReservationModel model;

	@Override
	protected AbstractModel<Reservation> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from Reservation t ";
	}

}
