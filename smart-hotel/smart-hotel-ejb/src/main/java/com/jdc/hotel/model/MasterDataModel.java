package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.MasterData;

@LocalBean
@Stateless
public class MasterDataModel extends AbstractModel<MasterData> {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Event<MasterData> event;

	@Override
	protected Class<MasterData> getType() {
		return MasterData.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

	@Override
	public void save(MasterData t) {
		super.save(t);
		event.fire(t);
	}

}
