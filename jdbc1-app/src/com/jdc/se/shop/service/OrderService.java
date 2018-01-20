package com.jdc.se.shop.service;

import java.time.LocalDate;
import java.util.List;

import com.jdc.se.shop.service.vo.OrderVO;

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

	public List<OrderVO> search(LocalDate value, LocalDate value2, String text, String text2) {
		// TODO Auto-generated method stub
		return null;
	}

}
