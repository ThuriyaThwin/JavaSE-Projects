package com.jdc.teacher.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.teacher.common.ConnectionManager;
import com.jdc.teacher.common.TeacherApplicationException;
import com.jdc.teacher.entity.ClassRoom;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.Student;

public class StudentModel {

	public void save(Student s) {
		
		String sql = "insert into student values (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, getStudentId(s.getYear(), s.getGrade()));
			stmt.setString(2, s.getGrade().name());
			stmt.setString(3, s.getYear());
			stmt.setString(4, s.getName());
			stmt.setString(5, s.getPhone());
			stmt.setString(6, s.getRemark());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
	}

	private int getStudentId(String year, Grade grade) {
		
		String sql = "select count(*) from student where grade = ? and year = ?";

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, grade.name());
			stmt.setString(2, year);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Long count = rs.getLong(1);
				return count.intValue() + 1;
			}

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
		
		return 1;
	}

	public List<Student> find(Grade grade, String year, String name) {
		
		List<Student> result = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder("select * from student where 1 = 1");
		List<Object> params = new ArrayList<>();
		
		if(null != grade) {
			sb.append(" and grade = ?");
			params.add(grade);
		}
		
		if(null != year && !year.isEmpty()) {
			sb.append(" and year = ?");
			params.add(year);
		}
		
		if(null != name && !name.isEmpty()) {
			sb.append(" and name like ?");
			params.add(name.concat("%"));
		}
		

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {

			for (int i = 0; i < params.size(); i++) {
				
				Object obj = params.get(i);
				if(obj instanceof Grade) {
					Grade g = (Grade) obj;
					obj = g.name();
				}
				
				stmt.setObject(i + 1, obj);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Student s = getData(rs);
				result.add(s);
			}
			
			
		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}

		return result;
	}

	private Student getData(ResultSet rs) throws SQLException {
		
		Student s = new Student();
		
		s.setId(rs.getInt("id"));
		
		ClassRoom room = new ClassRoom();
		room.setGrade(Grade.valueOf(rs.getString("grade")));
		room.setYear(rs.getString("year"));
		s.setRoom(room);
		s.setName(rs.getString("name"));
		s.setPhone(rs.getString("phone"));
		s.setRemark(rs.getString("remark"));
		
		return s;
	}

	public void update(Student s) {
		String sql = "update student set name = ?, phone = ?, remark = ? where id = ? and grade = ? and year = ?";

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, s.getName());
			stmt.setString(2, s.getPhone());
			stmt.setString(3, s.getRemark());
			stmt.setInt(4, s.getId());
			stmt.setString(5, s.getGrade().name());
			stmt.setString(6, s.getYear());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
	}

}
