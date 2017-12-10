package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.MasterData;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.MasterDataModel;

@Path("MasterData")
public class MasterDataResource extends AbstractResource<MasterData> {

	@Inject
	private MasterDataModel model;

	@Override
	protected AbstractModel<MasterData> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from MasterData t ";
	}

}
