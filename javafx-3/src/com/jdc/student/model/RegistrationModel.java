package com.jdc.student.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.student.model.entity.Registration;
import com.jdc.student.model.entity.Student;

public class RegistrationModel {
	
	private StudentModel studentModel;

	public RegistrationModel() {
		studentModel = new StudentModel();
	}
	
	public void create(Registration regis, Student student) {
		
		int studentId = student.getId();
		
		if(studentId == 0) {
			studentId = studentModel.create(student);
		}
		
		regis.setStudentId(studentId);
		
		String sql = "insert into registration (class_id, student_id, fees, paid) "
				+ "values (?, ?, ?, ?)";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, regis.getClassId());
			stmt.setInt(2, regis.getStudentId());
			stmt.setInt(3, regis.getPaid());
			stmt.setInt(4, regis.getFees());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Registration> findAll() {
		List<Registration> list = new ArrayList<>();
		
		String sql = "select r.id as id, r.class_id as classId, r.student_id as studentId, "
				+ "r.fees as fees, r.paid as paid, c.startDate as startDate, "
				+ "cs.name as courseName, s.name as studentName, s.phone as studentPhone "
				+ "from registration r "
				+ "inner join class c on r.class_id = c.id "
				+ "inner join course cs on c.course_id = cs.id "
				+ "inner join student s on r.student_id = s.id";

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

	private Registration getObject(ResultSet rs) throws SQLException {
		Registration r = new Registration();
		r.setId(rs.getInt("id"));
		r.setClassId(rs.getInt("classId"));
		r.setStudentId(rs.getInt("studentId"));
		r.setFees(rs.getInt("fees"));
		r.setPaid(rs.getInt("paid"));
		
		r.setStartDate(rs.getDate("startDate").toLocalDate().toString());
		r.setCourseName(rs.getString("courseName"));
		r.setStudentName(rs.getString("studentName"));
		r.setStudentPhone(rs.getString("studentPhone"));
		
		return r;
	}

}
