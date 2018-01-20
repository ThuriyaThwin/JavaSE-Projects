package com.jdc.se.shop.service.vo;

import java.time.LocalDate;
import java.util.List;

import com.jdc.online.entity.Order;
import com.jdc.online.entity.OrderDetails;

public class OrderVO {

	private Order order;
	private CustomerVO customer;
	private List<OrderDetails> detailsList;

	public int getId() {
		return order.getId();
	}

	public String getCustomerName() {
		return customer.getName();
	}

	public String getEmail() {
		return customer.getEmail();
	}

	public String getAddress() {
		return customer.getAddress();
	}

	public LocalDate getOrderDate() {
		return order.getOrderDate();
	}

	public int getItemCount() {
		return detailsList.stream().mapToInt(a -> a.getQuentity()).sum();
	}
	
	public int getSubTotal() {
		return order.getSubTotal();
	}
	
	public int getTax() {
		return order.getTax();
	}
	
	public int getTotal() {
		return getTax() + getSubTotal();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVO customer) {
		this.customer = customer;
	}

	public List<OrderDetails> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<OrderDetails> detailsList) {
		this.detailsList = detailsList;
	}

}
