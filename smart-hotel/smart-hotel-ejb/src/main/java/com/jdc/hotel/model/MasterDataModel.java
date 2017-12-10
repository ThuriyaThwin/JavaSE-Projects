package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.MasterData;

@LocalBean
@Stateless
public class MasterDataModel extends AbstractModel<MasterData> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<MasterData> getType() {
		return MasterData.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
