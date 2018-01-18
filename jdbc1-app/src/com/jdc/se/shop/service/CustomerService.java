package com.jdc.se.shop.service;

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

}
