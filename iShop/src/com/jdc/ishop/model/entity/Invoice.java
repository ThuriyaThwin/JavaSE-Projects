package com.jdc.ishop.model.entity;

import java.time.LocalDateTime;

public class Invoice {

	public Invoice() {
	}

	private int id;

	private LocalDateTime invoiceDate;

	private String saleEmployee;

	private String saleEmployeeName;

	private int count;

	private int subTotal;

	private int tax;

	private int total;

	private boolean delete;

	private String modifiedUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSaleEmployee() {
		return saleEmployee;
	}

	public void setSaleEmployee(String saleEmployee) {
		this.saleEmployee = saleEmployee;
	}

	public String getSaleEmployeeName() {
		return saleEmployeeName;
	}

	public void setSaleEmployeeName(String saleEmployeeName) {
		this.saleEmployeeName = saleEmployeeName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

}