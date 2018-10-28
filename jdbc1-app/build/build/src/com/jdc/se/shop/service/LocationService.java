package com.jdc.se.shop.service;

import java.util.Arrays;
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
	
	public void uploadStates(List<State> states) {
		for (State state : states) {
			stateRepo.insert(state);
		}
	}
	
	public void uploadTownships(List<Township> townships) {
		for (Township township : townships) {
			townshipRepo.insert(township);
		}
	}
	
	public State findStateById(int id) {
		return stateRepo.findById(id);
	}
	
	public List<State> getAllState() {
		return stateRepo.getAll();
	}
	
	public List<Township> findTownshipByState(State state) {
		return townshipRepo.search("and state_id = ?", Arrays.asList(state.getId()));
	}

}
