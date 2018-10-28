package com.jdc.se.shop.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jdc.online.entity.Category;
import com.jdc.online.entity.Product;
import com.jdc.online.repo.ProductRepo;
import com.jdc.se.shop.service.vo.ProductVO;

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
		return repo.search("and `category_id` = ?", Arrays.asList(c.getId()));
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

	public List<ProductVO> findVoByCategory(Category c) {

		return findByCategory(c).stream().map(p -> {
			ProductVO vo = new ProductVO();
			vo.setProduct(p);
			vo.setCategory(c);
			return vo;
		}).collect(Collectors.toList());
	}

}
