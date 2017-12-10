package com.jdc.hotel.producer;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jdc.hotel.entity.MasterData;
import com.jdc.hotel.model.MasterDataModel;

@ApplicationScoped
public class MasterDataProducer {

	@Inject
	private MasterDataModel model;

	@Named
	@Produces
	private List<MasterData> masterDataList;

	public void load(@Observes MasterData m) {
		masterDataList = model.findByQuery("select m from MasterData m", Optional.empty());
	}

}
