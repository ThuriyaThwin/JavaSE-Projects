package com.jdc.student.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.student.model.entity.Course;

public class CourseModel {
	
	public void create(Course course) {
		
		String sql = "insert into course (name, fees, duration, description) "
				+ "values (?, ?, ?, ?)";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getFees());
			stmt.setDouble(3, course.getDuration());
			stmt.setString(4, course.getDescription());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Course findById(int id) {
		String sql = "select * from course where id = ?";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return getObject(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private Course getObject(ResultSet rs) throws SQLException {
		Course c = new Course();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setFees(rs.getInt("fees"));
		c.setDuration(rs.getDouble("duration"));
		c.setDescription(rs.getString("description"));
		return c;
	}

	public List<Course> findAll() {
		
		List<Course> list = new ArrayList<>();
		
		String sql = "select * from course";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(getObject(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

}
