package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.utils.DatabaseManager;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public void save(Category cat) {
		if(cat.getId() == 0) {
			create(cat);
		} else {
			update(cat);
		}
	}

	private void update(Category cat) {
		
		String sql = "";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Category cat) {
		String sql = "";
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> find(String name) {
		List<Category> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> find(boolean delete) {
		List<Category> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
