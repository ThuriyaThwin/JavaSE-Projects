package com.jdc.teacher.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdc.teacher.common.ConnectionManager;
import com.jdc.teacher.common.TeacherApplicationException;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.ClassRoom;
import com.jdc.teacher.entity.ExamResult;
import com.jdc.teacher.entity.ExamResult.Exam;
import com.jdc.teacher.entity.Student;

public class ExamResultModel {

	public void save(ExamResult data) {
		
		check(data);

		String sql = "insert into exam_result(student, grade, year, exam, burmese, english, maths) values (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, data.getStudent().getId());
			stmt.setString(2, data.getStudent().getGrade().name());
			stmt.setString(3, data.getStudent().getYear());
			stmt.setString(4, data.getExam().name());
			stmt.setInt(5, data.getBurmese());
			stmt.setInt(6, data.getEnglish());
			stmt.setInt(7, data.getMaths());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
	}

	private void check(ExamResult data) {
		
		if(!find(data.getGrade(), data.getExam(), data.getYear(), data.getName()).isEmpty()) {
			throw new TeacherApplicationException(String.format("Exam Result for %s is alrady exit.", data.getName()));
		}
		
	}

	public List<ExamResult> find(Grade grade, Exam exam, String year, String name) {
		List<ExamResult> list = new ArrayList<>();
		
		List<Object> params = new ArrayList<>();
		StringBuilder sb = new StringBuilder("select e.id, e.student, s.grade, s.year, s.name, s.phone, s.remark, e.exam, e.burmese, e.english, e.maths from exam_result e inner join student s on e.student = s.id and e.grade = s.grade and e.year = s.year where 1 = 1");
		
		
		if(null != grade) {
			sb.append(" and e.grade = ?");
			params.add(grade);
		}
		
		if(null != exam) {
			sb.append(" and e.exam = ?");
			params.add(exam);
		}
		
		if(null != year && !year.isEmpty()) {
			sb.append(" and e.year = ?");
			params.add(year);
		}
		
		if(null != name && !name.isEmpty()) {
			sb.append(" and s.name like ?");
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
				
				if(obj instanceof Exam) {
					Exam e = (Exam) obj;
					obj = e.name();
				}
				
				stmt.setObject(i + 1, obj);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ExamResult result = getData(rs);
				list.add(result);
			}

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
		
		return list;
	}

	private ExamResult getData(ResultSet rs) throws SQLException {
		ExamResult e = new ExamResult();
		
		Student s = new Student();
		s.setId(rs.getInt("student"));
		
		ClassRoom room = new ClassRoom();
		room.setGrade(Grade.valueOf(rs.getString("grade")));
		room.setYear(rs.getString("year"));
		s.setRoom(room);
		s.setName(rs.getString("name"));
		s.setPhone(rs.getString("phone"));
		s.setRemark(rs.getString("remark"));
		e.setStudent(s);
		e.setExam(Exam.valueOf(rs.getString("exam")));
		e.setId(rs.getInt("id"));
		e.setBurmese(rs.getInt("burmese"));
		e.setEnglish(rs.getInt("english"));
		e.setMaths(rs.getInt("maths"));
		
		return e;
	}

	public void update(ExamResult data) {
		
		String sql = "update exam_result set burmese = ?, english = ?, maths = ? where id = ?";

		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, data.getBurmese());
			stmt.setInt(2, data.getEnglish());
			stmt.setInt(3, data.getMaths());
			stmt.setInt(4, data.getId());

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
	}

}
