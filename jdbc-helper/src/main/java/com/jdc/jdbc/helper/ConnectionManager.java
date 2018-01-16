package com.jdc.jdbc.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {

	private static Properties props;
	
	static {
		try {
			props = new Properties();
			props.load(ConnectionManager.class.getResourceAsStream("/META-INF/db.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.pass"));
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
