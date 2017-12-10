package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.RoomServiceItem;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.RoomServiceItemModel;

@Path("RoomServiceItem")
public class RoomServiceItemResource extends AbstractResource<RoomServiceItem> {

	@Inject
	private RoomServiceItemModel model;

	@Override
	protected AbstractModel<RoomServiceItem> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from RoomServiceItem t ";
	}

}
