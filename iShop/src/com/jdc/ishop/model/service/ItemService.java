package com.jdc.ishop.model.service;

import java.util.List;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.service.impl.ItemServiceImpl;

public interface ItemService {

	void save(Item t);

	List<Item> find(Category category, String priceFrom, String priceTo, String name);

	static ItemService getInstance() {
		return new ItemServiceImpl();
	}

}