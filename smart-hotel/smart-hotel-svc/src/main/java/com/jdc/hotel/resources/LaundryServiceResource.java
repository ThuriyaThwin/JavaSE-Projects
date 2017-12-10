package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.LaundryService;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.LaundryServiceModel;

@Path("LaundryService")
public class LaundryServiceResource extends AbstractResource<LaundryService> {

	@Inject
	private LaundryServiceModel model;

	@Override
	protected AbstractModel<LaundryService> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from LaundryService t ";
	}

}
