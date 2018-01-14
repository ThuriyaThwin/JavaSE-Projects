package com.jdc.online.repo;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.online.entity.Category;

public class CategoryRepo extends AbstractRepo<Category> {


	public CategoryRepo() {
		super(Category.class);
	}

}
