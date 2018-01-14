package com.jdc.online.entity;

import java.time.LocalDate;

import com.jdc.jdbc.helper.BaseEntity;
import com.jdc.jdbc.helper.Column;
import com.jdc.jdbc.helper.Id;
import com.jdc.jdbc.helper.Table;

@Table("orders")
public class Order implements BaseEntity {

	@Id
	private int id;
	private LocalDate orderDate;
	private int subTotal;
	private int tax;

	@Column("deliveryFees")
	private int delivery;

	@Column("customer_id")
	private int customerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public int getDelivery() {
		return delivery;
	}

	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
