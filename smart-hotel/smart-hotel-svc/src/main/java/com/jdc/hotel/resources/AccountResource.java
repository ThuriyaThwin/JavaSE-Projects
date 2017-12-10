package com.jdc.hotel.resources;

import javax.inject.Inject;
import javax.ws.rs.Path;

import com.jdc.hotel.entity.Account;
import com.jdc.hotel.model.AbstractModel;
import com.jdc.hotel.model.AccountModel;

@Path("Account")
public class AccountResource extends AbstractResource<Account> {

	@Inject
	private AccountModel model;

	@Override
	protected AbstractModel<Account> getModel() {
		return model;
	}

	@Override
	protected String getAllQuery() {
		return "select t from Account t ";
	}

}
