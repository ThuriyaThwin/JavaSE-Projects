package com.jdc.se.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.jdc.online.entity.Customer;
import com.jdc.online.entity.Township;
import com.jdc.online.repo.CustomerRepo;
import com.jdc.online.repo.StateRepo;
import com.jdc.online.repo.TownshipRepo;
import com.jdc.se.shop.service.vo.CustomerVO;

public class CustomerService {
	
	private static CustomerService service;
	
	private CustomerRepo repo;
	private TownshipRepo townshipRepo;
	private StateRepo stateRepo;
	
	public static CustomerService getService() {
		
		if(null == service) {
			service = new CustomerService();
		}
		
		return service;
	}
	
	private CustomerService() {
		repo = new CustomerRepo();
		townshipRepo = new TownshipRepo();
		stateRepo = new StateRepo();
	}

	public List<CustomerVO> find(Township township, String name, String email) {
		
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		if(null != township) {
			sb.append("and township_id = ? ");
			params.add(township.getId());
		}
		
		if(null != name && !name.trim().isEmpty()) {
			sb.append("and name like ? ");
			params.add(name.trim().concat("%"));
		}
		
		if(null != email && !email.trim().isEmpty()) {
			sb.append("and email like ? ");
			params.add(email.trim().concat("%"));
		}	

		return findVO(repo.search(sb.toString(), params));
	}

	private List<CustomerVO> findVO(List<Customer> list) {
		List<CustomerVO> result = new ArrayList<>();
		
		for(Customer c : list) {
			result.add(findById(c.getId()));
		}
		
		return result;
	}
	
	public CustomerVO findById(int id) {
		CustomerVO vo = new CustomerVO();
		vo.setCustomer(repo.findById(id));
		vo.setTownship(townshipRepo.findById(vo.getCustomer().getTownshipId()));
		vo.setState(stateRepo.findById(vo.getTownship().getStateId()));
		
		return vo;
	}

}
