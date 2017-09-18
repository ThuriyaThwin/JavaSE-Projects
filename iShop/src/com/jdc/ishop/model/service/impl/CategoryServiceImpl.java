package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.utils.DatabaseManager;
import com.jdc.ishop.utils.Security;

public class CategoryServiceImpl implements CategoryService {
	
	private static final String SELECT = "select c.id id, c.create_user createUser, "
			+ "c.creation creation, c.del_flag deleteFlag, "
			+ "m.name createUserName, c.name name "
			+ "from category c join member m on c.create_user = m.login ";

	@Override
	public void save(Category cat) {
		
		if(cat.getName() == null || cat.getName().isEmpty()) {
			throw new IShopException("Please Enter Category Name.");
		}
		
		if(cat.getId() == 0) {
			create(cat);
		} else {
			update(cat);
		}
	}

	private void update(Category cat) {
		
		check(cat);

		String sql = "update category set name = ?, del_flag = ?, create_user = ? where id = ?";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, cat.getName());
			stmt.setBoolean(2, cat.isDelete());
			stmt.setString(3, Security.getLoginUser().getLogin());
			stmt.setInt(4, cat.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Category cat) {
		
		check(cat);
		
		String sql = "insert into category (name, creation, create_user) values (?, ?, ?)";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, cat.getName());
			stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(3, Security.getLoginUser().getLogin());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void check(Category cat) {
		if(cat.getName() == null || cat.getName().isEmpty()) {
			throw new IShopException("Please enter Category Name.");
		}
	}

	@Override
	public List<Category> find(String name) {
		
		List<Category> list = new ArrayList<>();
		List<String> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder(SELECT);

		if(!name.isEmpty()) {
			sb.append("where name like ?");
			params.add(name.concat("%"));
		}
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setString(i + 1, params.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getObject(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> find(boolean delete) {
		
		List<Category> list = new ArrayList<>();
		String sql = SELECT.concat(" where c.del_flag = ?");

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setBoolean(1, delete);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private Category getObject(ResultSet rs) throws SQLException {
		Category c = new Category();
		
		c.setId(rs.getInt("id"));
		c.setCreateUser(rs.getString("createUser"));
		c.setCreation(rs.getTimestamp("creation").toLocalDateTime());
		c.setCreateUserName(rs.getString("createUserName"));
		c.setDelete(rs.getBoolean("deleteFlag"));
		c.setName(rs.getString("name"));
		return c;
	}

	@Override
	public long findCount() {
		String sql = "select count(*) from category where del_flag = 0";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
