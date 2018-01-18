package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.State;

public class StateRepo extends AbstractRepo<State> {

	public StateRepo() {
		super(State.class);
	}

}
