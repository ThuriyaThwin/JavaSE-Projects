package com.jdc.se.shop.service;

import java.util.List;

import com.jdc.online.entity.State;
import com.jdc.online.entity.Township;
import com.jdc.online.repo.StateRepo;
import com.jdc.online.repo.TownshipRepo;

public class LocationService {
	
	private TownshipRepo townshipRepo;
	private StateRepo stateRepo;
	
	private static LocationService service;
	
	public static LocationService getService() {
		
		if(service == null) {
			service = new LocationService();
		}
		
		return service;
	}
	
	private LocationService() {
		townshipRepo = new TownshipRepo();
		stateRepo = new StateRepo();
	}
	
	public State findStateById(int id) {
		return stateRepo.findById(id);
	}
	
	public List<State> getAllState() {
		return stateRepo.getAll();
	}
	
	public List<Township> findTownshipByState(State state) {
		return townshipRepo.findByStateId(state.getId());
	}

}
