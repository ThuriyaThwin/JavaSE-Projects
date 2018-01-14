package com.jdc.online.entity;

import com.jdc.jdbc.helper.BaseEntity;
import com.jdc.jdbc.helper.Column;
import com.jdc.jdbc.helper.Id;
import com.jdc.jdbc.helper.Table;

@Table("order_details")
public class OrderDetails implements BaseEntity{

	@Id
	private int id;
	@Column("order_id")
	private int orderId;
	@Column("product_id")
	private int productId;
	private int quentity;
	private int unitPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuentity() {
		return quentity;
	}

	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

}
