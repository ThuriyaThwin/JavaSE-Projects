package com.jdc.se.shop.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jdc.online.entity.Order;
import com.jdc.online.entity.OrderDetails;
import com.jdc.online.repo.OrderDetailsRepo;
import com.jdc.online.repo.OrderRepo;
import com.jdc.se.shop.service.vo.OrderVO;

public class OrderService {
	
	private static OrderService service;
	
	private OrderRepo orderRepo;
	private OrderDetailsRepo detailsRepo;
	private CustomerService customerService;
	
	public static OrderService getService() {
		
		if(null == service) {
			service = new OrderService();
		}
		
		return service;
	}
	
	private OrderService() {
		orderRepo = new OrderRepo();
		detailsRepo = new OrderDetailsRepo();
		customerService = CustomerService.getService();
	}

	public List<OrderVO> search(LocalDate dateFrom, LocalDate dateTo, String custName) {
		
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		if(null != dateFrom) {
			sb.append("and orderDate >= ? ");
			params.add(Date.valueOf(dateFrom));
		}
		
		if(null != dateTo) {
			sb.append("and orderDate <= ? ");
			params.add(Date.valueOf(dateTo));
		}
		
		if(null != custName && !custName.trim().isEmpty()) {
			sb.append("and customer_id in (select id from customer where name like ?) ");
			params.add(custName.trim().concat("%"));
		}
		
		return getVO(orderRepo.search(sb.toString(), params));
	}

	private List<OrderVO> getVO(List<Order> list) {
		
		List<OrderVO> result = new ArrayList<>();
		
		for(Order o : list) {
			OrderVO vo = getVO(o);
			result.add(vo);
		}
		
		return result;
	}

	private OrderVO getVO(Order o) {
		OrderVO vo = new OrderVO();
		vo.setOrder(o);
		vo.setCustomer(customerService.findById(vo.getOrder().getCustomerId()));
		
		List<OrderDetails> details = findDetailsByOrderId(o.getId());
		vo.setDetailsList(details);
		
		return vo;
	}

	private List<OrderDetails> findDetailsByOrderId(int id) {
		return detailsRepo.search("and order_id = ?", Arrays.asList(id));
	}

}
