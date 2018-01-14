package com.jdc.online.model.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel<T extends BaseEntity> {

	protected TableInfoHelper<T> helper;
	
	public AbstractModel(Class<T> type) {
		helper = new TableInfoHelper<>(type);
	}
	
	private String tableName() {
		return helper.getTable();
	}
	public void insert(T state) {
		String sql = String.format("insert into %s (%s) values (%s)", tableName(), helper.getInsertString(), helper.getInsertParamString());

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			List<Object> params = helper.getInsertParams(state);
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				state.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(T state) {

		String sql = String.format("update %s set %s where %s", tableName(), helper.getUpdateSetString(), helper.getUpdateWhereString());

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			List<Object> params = helper.getUpdateParams(state);
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String sql = String.format("delete from %s where id = ?", tableName());
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<T> getAll() {
		String sql = String.format("select * from %s", tableName());
		List<T> list = new ArrayList<>();
		// get connection
		// create statement
		try (Connection conn = ConnectionManager.getConnection(); 
				Statement stmt = conn.createStatement()) {
			// execute sql
			ResultSet rs = stmt.executeQuery(sql);

			// get results from result set
			while (rs.next()) {
				T s = helper.getData(rs);
				list.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public T findById(int id) {
		String sql = String.format("select * from %s where id = ?", tableName());

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return helper.getData(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}