package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.service.ItemService;
import com.jdc.ishop.utils.DatabaseManager;

public class ItemServiceImpl implements ItemService{

	@Override
	public void save(Item t) {
		if(t.getId() == 0) {
			create(t);
		} else {
			update(t);
		}
	}

	private void update(Item t) {
		String sql = "";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Item t) {
		String sql = "";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> find(Category category, String priceFrom, String priceTo, String name) {
		List<Item> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
