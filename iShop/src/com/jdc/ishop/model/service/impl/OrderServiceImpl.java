package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.SaleOrder;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.model.service.OrderService;
import com.jdc.ishop.utils.DatabaseManager;

public class OrderServiceImpl implements OrderService {
	
	private static final String SELECT = "select s.id id, s.invoice_id invoiceId, "
			+ "s.item_id itemId, s.unit_price unitPrice, s.quentity quentity, "
			+ "s.total total, c.name categoryName, it.name itemName, i.invoice_date saleDate, "
			+ "m.name employeeName from sale_item s "
			+ "inner join invoice i on s.invoice_id = i.id "
			+ "inner join item it on s.item_id = it.id "
			+ "inner join category c on it.category_id = c.id "
			+ "join member m on i.sale_employee = m.login "
			+ "where i.del_flag = 0 ";
	
	private CategoryService catService;
	
	public OrderServiceImpl() {
		catService = CategoryService.getInstance();
	}

	@Override
	public List<SaleOrder> find(Category category, LocalDate dateFrom, LocalDate dateTo, String employeeName) {
		
		List<SaleOrder> list = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder(SELECT);
		List<Object> params = new ArrayList<>();
		
		if(null != category) {
			sb.append("and c.id = ? ");
			params.add(category.getId());
		}
		
		if(null != dateFrom) {
			sb.append("and i.invoice_date >= ? ");
			params.add(Timestamp.valueOf(dateFrom.atStartOfDay()));
		}

		if(null != dateTo) {
			sb.append("and i.invoice_date < ? ");
			params.add(Timestamp.valueOf(dateTo.plusDays(1).atStartOfDay()));
		}
		
		if(!employeeName.isEmpty()) {
			sb.append("and (i.sale_employee like ? or m.name like ? ) ");
			params.add(employeeName.concat("%"));
			params.add(employeeName.concat("%"));
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

	private SaleOrder getObject(ResultSet rs) throws SQLException {
		SaleOrder s = new SaleOrder();
		
		s.setId(rs.getInt("id"));
		s.setInvoiceId(rs.getInt("invoiceId"));
		s.setItemId(rs.getInt("itemId"));
		s.setPrice(rs.getInt("unitPrice"));
		s.setCount(rs.getInt("quentity"));
		s.setCategory(rs.getString("categoryName"));
		s.setEmployeeName(rs.getString("employeeName"));
		s.setItemName(rs.getString("itemName"));
		s.setSaleDate(rs.getTimestamp("saleDate").toLocalDateTime().toLocalDate());
		s.setTotal(rs.getInt("total"));
		
		return s;
	}

	@Override
	public Map<String, Map<String, Integer>> findData(LocalDate dateFrom, LocalDate dateTo) {
		
		if(null == dateFrom) {
			throw new IShopException("Please enter Date From Value.");
		}
		
		if(null == dateTo) {
			throw new IShopException("Please enter Date To Value.");
		}

		Map<String, Map<String, Integer>> map = new LinkedHashMap<>();
		
		List<Category> list = catService.find(false);
		
		for (Category category : list) {
			map.put(category.getName(), findData(category, dateFrom, dateTo));
		}
		return map;
	}
	
	private Map<String, Integer> findData(Category category, LocalDate dateFrom, LocalDate dateTo) {
		Map<String, Integer> map = new LinkedHashMap<>();
		
		String sql = "select c.name category, sum(s.quentity) quentity from sale_item s "
				+ "inner join invoice iv on s.invoice_id = iv.id "
				+ "inner join item it on s.item_id = it.id "
				+ "inner join category c on it.category_id = c.id "
				+ "where c.id = ? and iv.del_flag = 0 and iv.invoice_date >= ? and iv.invoice_date < ? "
				+ "group by c.id";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			LocalDate target = dateFrom;
			
			while(target.compareTo(dateTo) <= 0) {
				stmt.setInt(1, category.getId());
				stmt.setTimestamp(2, Timestamp.valueOf(target.atStartOfDay()));
				stmt.setTimestamp(3, Timestamp.valueOf(target.plusDays(1).atStartOfDay()));
				
				ResultSet rs = stmt.executeQuery();
				
				String key = target.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				int value = 0;
				
				while(rs.next()) {
					Long count = rs.getLong(2);
					value = count.intValue();
				}
				
				map.put(key, value);
				
				target = target.plusDays(1);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return map;
	}

	@Override
	public void create(SaleOrder s) {
		
		String sql = "insert into sale_item (invoice_id, item_id, unit_price, quentity, total) "
				+ "values (?, ?, ?, ?, ?)";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, s.getInvoiceId());
			stmt.setInt(2, s.getItemId());
			stmt.setInt(3, s.getPrice());
			stmt.setInt(4, s.getCount());
			stmt.setInt(5, s.getTotal());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
