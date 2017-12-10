package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.RoomService;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.RoomServiceModel;

@Path("RoomService")
public class RoomServiceResource extends AbstractResource<RoomService> {

	@Inject
	private RoomServiceModel model;

	@Override
	protected AbstractModel<RoomService> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from RoomService t ";
	}

}
