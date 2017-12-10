package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.RoomReservation;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.RoomReservationModel;

@Path("RoomReservation")
public class RoomReservationResource extends AbstractResource<RoomReservation> {

	@Inject
	private RoomReservationModel model;

	@Override
	protected AbstractModel<RoomReservation> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from RoomReservation t ";
	}

}
