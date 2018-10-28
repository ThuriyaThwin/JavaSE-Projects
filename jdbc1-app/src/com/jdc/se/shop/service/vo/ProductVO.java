package com.jdc.se.shop.service.vo;

import com.jdc.online.entity.Category;
import com.jdc.online.entity.Product;

public class ProductVO {

	private Product product;
	private Category category;
	
	public String getCategoryName() {
		return category.getName();
	}
	
	public String getProductName() {
		return product.getName();
	}
	
	public int getPrice() {
		return product.getPrice();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
