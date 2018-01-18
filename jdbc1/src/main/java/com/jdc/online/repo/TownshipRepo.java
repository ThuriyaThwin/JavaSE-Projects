package com.jdc.online.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.jdbc.helper.AbstractRepo;
import com.jdc.jdbc.helper.ConnectionManager;
import com.jdc.online.entity.Township;

public class TownshipRepo extends AbstractRepo<Township>{

	public TownshipRepo() {
		super(Township.class);
	}

	public List<Township> findByNameLike(String name) {
		List<Township> list = new ArrayList<>();
		String sql = "select * from township where name like ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name.concat("%"));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(helper.getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Township> findByStateId(int stateId) {
		List<Township> list = new ArrayList<>();
		String sql = "select * from township where state_id = ?";
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, stateId);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println(sql);

			while (rs.next()) {
				list.add(helper.getData(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
