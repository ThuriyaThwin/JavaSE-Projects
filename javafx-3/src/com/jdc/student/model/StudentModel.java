package com.jdc.student.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdc.student.model.entity.Student;

public class StudentModel {

	public List<Student> search(String name, String phone) {
		
		List<Student> list = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder("select * from student where 1 = 1 ");
		List<String> params = new ArrayList<>();
		
		if(null != name && !name.isEmpty()) {
			sb.append("and name like ? ");
			params.add(name.concat("%"));
		}
		
		if(null != phone && !phone.isEmpty()) {
			sb.append("and phone like ? ");
			params.add(phone.concat("%"));
		}
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for (int i = 0; i < params.size(); i++) {
				stmt.setString(i = 1, params.get(i));
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

	private Student getObject(ResultSet rs) throws SQLException {
		Student s = new Student();
		
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		s.setPhone(rs.getString("phone"));
		s.setEmail(rs.getString("email"));
		
		return s;
	}

	public int create(Student student) {
		
		String sql = "insert into student (name, phone, email) values (?, ?, ?)";
		

		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getPhone());
			stmt.setString(3, student.getEmail());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			while(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}

}
