package com.jdc.se.shop.service;

import java.util.List;

import com.jdc.online.entity.State;
import com.jdc.online.entity.Township;
import com.jdc.se.shop.service.vo.CustomerVO;

public class CustomerService {
	
	private static CustomerService service;
	
	public static CustomerService getService() {
		
		if(null == service) {
			service = new CustomerService();
		}
		
		return service;
	}
	
	private CustomerService() {
		// TODO Auto-generated constructor stub
	}

	public List<CustomerVO> find(State value, Township value2, String text, String text2, String text3) {
		// TODO Auto-generated method stub
		return null;
	}

}
