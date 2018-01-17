package com.jdc.se.shop.service;

import java.util.List;

import com.jdc.online.entity.Category;
import com.jdc.online.repo.CategoryRepo;

public class CategoryService {
	
	private static CategoryService service;
	
	private CategoryRepo repo;
	
	public static CategoryService getService() {
		
		if(null == service) {
			service = new CategoryService();
		}
		
		return service;
	}
	
	private CategoryService() {
		repo = new CategoryRepo();
	}
	
	public List<Category> getAll() {
		return repo.getAll();
	}
	
	public void upload(List<Category> data) {
		for(Category c : data) {
			save(c);
		}
	}
	
	public void save(Category c) {
		if(c.getId() == 0) {
			repo.insert(c);
		} else {
			repo.update(c);
		}
	}

}
