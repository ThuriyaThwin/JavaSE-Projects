package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.Room;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.RoomModel;

@Path("Room")
public class RoomResource extends AbstractResource<Room> {

	@Inject
	private RoomModel model;

	@Override
	protected AbstractModel<Room> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from Room t ";
	}

}
