package com.jdc.ishop.model.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Invoice;
import com.jdc.ishop.model.entity.SaleOrder;
import com.jdc.ishop.model.service.InvoiceService;
import com.jdc.ishop.model.service.OrderService;
import com.jdc.ishop.utils.DatabaseManager;

public class InvoiceServiceImpl implements InvoiceService {
	
	private static final String SELECT = "select distinct iv.id id, iv.invoice_date invoiceDate, "
			+ "iv.sale_employee saleEmployee, iv.quentity quentity, iv.sub_total subTotal, "
			+ "iv.total total, iv.tax tax, iv.total total, iv.del_flag delFlag, "
			+ "iv.modfied_user modUser, m.name saleEmployeeName "
			+ "from Invoice iv "
			+ "inner join sale_item si on iv.id = si.invoice_id "
			+ "inner join member m on iv.sale_employee = m.login "
			+ "inner join item it on si.item_id = it.id "
			+ "where iv.del_flag = 0 ";

	
	@Override
	public List<Invoice> find(String login, Category category, LocalDate dateFrom, LocalDate dateTo) {
		
		if(dateFrom != null && dateTo != null && dateFrom.compareTo(dateTo) > 0) {
			throw new IShopException("Date To must be greater than Date To Value.");
		}
		
		List<Invoice> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder(SELECT);
		List<Object> params = new ArrayList<>();
		
		if(null != login && !login.isEmpty()) {
			sb.append("and iv.sale_employee = ? ");
			params.add(login);
		}
		
		if(null != category) {
			sb.append("and it.category_id = ? ");
			params.add(category.getId());
		}
		
		if(null != dateFrom) {
			sb.append("and iv.invoice_date >= ? ");
			params.add(Timestamp.valueOf(dateFrom.atStartOfDay()));
		}
		
		if(null != dateTo) {
			sb.append("and iv.invoice_date < ? ");
			params.add(Timestamp.valueOf(dateTo.plusDays(1).atStartOfDay()));
		}
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return result;
	}

	private Invoice getObject(ResultSet rs) throws SQLException {
		Invoice i = new Invoice();
		i.setCount(rs.getInt("quentity"));
		i.setDelete(rs.getBoolean("delFlag"));
		i.setId(rs.getInt("id"));
		i.setInvoiceDate(rs.getTimestamp("invoiceDate").toLocalDateTime());
		i.setModifiedUser(rs.getString("modUser"));
		i.setSaleEmployee(rs.getString("saleEmployee"));
		i.setSaleEmployeeName(rs.getString("saleEmployeeName"));
		i.setSubTotal(rs.getInt("subTotal"));
		i.setTax(rs.getInt("tax"));
		i.setTotal(rs.getInt("total"));
		return i;
	}

	@Override
	public void create(Invoice invoice, List<SaleOrder> orders) {
		
		create(invoice);
		
		OrderService service = OrderService.getInstance();
		
		for(SaleOrder s : orders) {
			s.setInvoiceId(invoice.getId());
			service.create(s);
		}
		
	}
	
	private void create(Invoice s) {
		String sql = "insert into invoice (invoice_date, sale_employee, quentity, sub_total, tax, total, modfied_user) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = DatabaseManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setTimestamp(1, Timestamp.valueOf(s.getInvoiceDate()));
			stmt.setString(2, s.getSaleEmployee());
			stmt.setInt(3, s.getCount());
			stmt.setInt(4, s.getSubTotal());
			stmt.setInt(5, s.getTax());
			stmt.setInt(6, s.getTotal());
			stmt.setString(7, s.getModifiedUser());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				s.setId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
