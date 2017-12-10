package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.RoomType;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.RoomTypeModel;

@Path("RoomType")
public class RoomTypeResource extends AbstractResource<RoomType> {

	@Inject
	private RoomTypeModel model;

	@Override
	protected AbstractModel<RoomType> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from RoomType t ";
	}

}
