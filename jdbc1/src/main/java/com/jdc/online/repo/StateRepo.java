package com.jdc.online.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.jdbc.helper.ConnectionManager;
import com.jdc.online.entity.State;

public class StateRepo extends AbstractRepo<State> {

	public StateRepo() {
		super(State.class);
	}

	public List<State> findByNameLike(String string) {

		List<State> result = new ArrayList<>();
		String sql = "select * from state where name like ?";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, string.concat("%"));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result.add(helper.getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
