package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.LaundryItem;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.LaundryItemModel;

@Path("LaundryItem")
public class LaundryItemResource extends AbstractResource<LaundryItem> {

	@Inject
	private LaundryItemModel model;

	@Override
	protected AbstractModel<LaundryItem> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from LaundryItem t ";
	}

}
