package com.jdc.ishop.model.service;

import java.util.List;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.service.impl.CategoryServiceImpl;

public interface CategoryService {

	static CategoryService getInstance() {
		return new CategoryServiceImpl();
	}

	void save(Category cat);

	List<Category> find(String name);
	List<Category> find(boolean delete);

	long findCount();

}