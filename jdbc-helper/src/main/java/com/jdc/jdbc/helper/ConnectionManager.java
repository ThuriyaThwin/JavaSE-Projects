package com.jdc.jdbc.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	private static final String URL = "jdbc:mysql://localhost:3306/online_shop_db";
	private static final String PASS = "admin";
	private static final String USR = "root";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USR, PASS);
	}

	public static void truncate(String... strings) {
		try (Connection conn = ConnectionManager.getConnection(); Statement stmt = conn.createStatement()) {
			// off fk check
			stmt.execute("set foreign_key_checks = 0");

			// truncates
			for (String string : strings) {
				String sql = String.format("truncate table %s", string);
				stmt.execute(sql);
			}

			// on fk check
			stmt.execute("set foreign_key_checks = 1");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
