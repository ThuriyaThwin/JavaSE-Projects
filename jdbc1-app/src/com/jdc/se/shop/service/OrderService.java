package com.jdc.se.shop.service;

public class OrderService {
	
	private static OrderService service;
	
	public static OrderService getService() {
		
		if(null == service) {
			service = new OrderService();
		}
		
		return service;
	}
	
	private OrderService() {
		// TODO Auto-generated constructor stub
	}

}
