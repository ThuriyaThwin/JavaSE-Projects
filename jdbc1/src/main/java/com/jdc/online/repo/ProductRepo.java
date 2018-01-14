package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.Product;

public class ProductRepo extends AbstractRepo<Product> {

	public ProductRepo() {
		super(Product.class);
	}

}
