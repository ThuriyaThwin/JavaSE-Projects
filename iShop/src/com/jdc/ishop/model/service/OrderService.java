package com.jdc.ishop.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.SaleOrder;
import com.jdc.ishop.model.service.impl.OrderServiceImpl;

public interface OrderService {

	static OrderService getInstance() {
		return new OrderServiceImpl();
	}

	List<SaleOrder> find(Category category, LocalDate dateFrom, LocalDate dateTo, String employeeName);

	Map<String, Map<String, Integer>> findData(LocalDate value, LocalDate value2);

}