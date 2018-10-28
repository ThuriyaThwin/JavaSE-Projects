package com.jdc.se.shop.service;

import java.util.Arrays;
import java.util.List;

import com.jdc.online.entity.Category;
import com.jdc.online.entity.Product;
import com.jdc.online.repo.ProductRepo;

public class ProductService {
	
	private static ProductService service;
	
	private ProductRepo repo;
	
	public static ProductService getService() {
		
		if(null == service) {
			service = new ProductService();
		}
		
		return service;
	}
	
	private ProductService() {
		repo = new ProductRepo();
	}

	public List<Product> findByCategory(Category c) {
		return repo.search("`category_id` = ?", Arrays.asList(c.getId()));
	}

	public void save(Product t) {
		if(t.getId() == 0) {
			repo.insert(t);
		} else {
			repo.update(t);
		}
	}

	public void save(List<Product> products) {
		for(Product p : products) {
			save(p);
		}
	}

}
