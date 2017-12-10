package com.jdc.hotel.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.hotel.entity.Account;

@LocalBean
@Stateless
public class AccountModel extends AbstractModel<Account> {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected Class<Account> getType() {
		return Account.class;
	}

	@Override
	protected EntityManager getEm() {
		return em;
	}

}
