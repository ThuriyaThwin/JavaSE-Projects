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
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.service.ItemService;
import com.jdc.ishop.utils.DatabaseManager;
import com.jdc.ishop.utils.Security;

public class ItemServiceImpl implements ItemService{
	
	private static final String SELECT = "select i.id id, i.name name, i.price price, "
			+ "i.category_id categoryId, c.name categoryName, i.barcode barcode, "
			+ "i.del_flag deleteFlag, i.creation creation, i.create_user createUser "
			+ "from item i inner join category c on i.category_id = c.id where 1 = 1 ";

	@Override
	public void save(Item t) {
		
		check(t);
		
		if(t.getId() == 0) {
			create(t);
		} else {
			update(t);
		}
	}

	private void check(Item t) {
		// category
		if(t.getCategoryId() == 0) {
			throw new IShopException("Please select Category!");
		}
		
		// name
		if(null == t.getName() || t.getName().isEmpty()) {
			throw new IShopException("Please enter Item Name.");
		}
		
		// price
		if(t.getPrice() == 0) {
			throw new IShopException("Please enter Item Price.");
		}
		
	}

	private void update(Item t) {
		String sql = "update item set name = ?, price = ?, barcode = ?, category_id = ?, "
				+ "del_flag = ?, creation = ?, create_user = ? where id = ?";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, t.getName());
			stmt.setInt(2, t.getPrice());
			stmt.setString(3, t.getBarcode());
			stmt.setInt(4, t.getCategoryId());
			stmt.setBoolean(5, t.isDelete());
			stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(7, Security.getLoginUser().getLogin());
			stmt.setInt(8, t.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void create(Item t) {
		String sql = "insert into item (name, price, barcode, category_id, del_flag, creation, create_user) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, t.getName());
			stmt.setInt(2, t.getPrice());
			stmt.setString(3, t.getBarcode());
			stmt.setInt(4, t.getCategoryId());
			stmt.setBoolean(5, t.isDelete());
			stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setString(7, Security.getLoginUser().getLogin());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Item> find(Category category, String priceFrom, String priceTo, String name) {

		List<Item> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder(SELECT);
		List<Object> params = new ArrayList<>();

		try {
			if(null != category) {
				sb.append("and i.category_id = ? ");
				params.add(category.getId());
			}
			
			if(!priceFrom.isEmpty()) {
				sb.append("and i.price >= ? ");
				params.add(Integer.parseInt(priceFrom));
			}
			
			if(!priceTo.isEmpty()) {
				sb.append("and i.price <= ? ");
				params.add(Integer.parseInt(priceTo));
			}
			
			if(!name.isEmpty()) {
				sb.append("and i.name like ? ");
				params.add(name.concat("%"));
			}
			
		} catch (NumberFormatException e) {
			throw new IShopException("Please enter price value with Digit.");
		}

		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
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

	private Item getObject(ResultSet rs) throws SQLException {
		Item i = new Item();
		
		i.setId(rs.getInt("id"));
		i.setName(rs.getString("name"));
		i.setBarcode(rs.getString("barcode"));
		i.setCategoryId(rs.getInt("categoryId"));
		i.setCategoryName(rs.getString("categoryName"));
		i.setCreateUser(rs.getString("createUser"));
		i.setCreation(rs.getTimestamp("creation").toLocalDateTime());
		i.setPrice(rs.getInt("price"));
		
		return i;
	}

}
