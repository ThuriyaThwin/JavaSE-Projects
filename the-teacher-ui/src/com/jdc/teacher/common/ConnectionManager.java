package com.jdc.teacher.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String USER = "root";
	private static final String PASS = "admin";
	private static final String URL = "jdbc:mysql://localhost:3306/teacher_db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
