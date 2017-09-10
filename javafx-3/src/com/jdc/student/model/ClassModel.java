package com.jdc.student.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdc.student.model.entity.Class;
import com.jdc.student.model.vo.ClassVO;

public class ClassModel {
	
	private CourseModel courseModel;
	
	public ClassModel() {
		courseModel = new CourseModel();
	}

	public void create(Class c) {
		String sql = "insert into class "
				+ "(startDate, duration, days, startTime, endTime, remark, course_id) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setDate(1, Date.valueOf(c.getStartDate()));
			stmt.setDouble(2, c.getDuration());
			stmt.setString(3, c.getDays());
			stmt.setString(4, c.getStartTime());
			stmt.setString(5, c.getEndTime());
			stmt.setString(6, c.getRemark());
			stmt.setInt(7, c.getCourse_id());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ClassVO findById(int id) {
		
		String sql = "select * from class where id = ?";

		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Class clz = getObject(rs);
				return getVO(clz);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private ClassVO getVO(Class clz) {
		if(clz != null) {
			ClassVO vo = new ClassVO();
			vo.setClassData(clz);
			vo.setCourse(courseModel.findById(clz.getCourse_id()));
			return vo;
		}
		return null;
	}

	private Class getObject(ResultSet rs) throws SQLException {
		Class c = new Class();
		c.setId(rs.getInt("id"));
		c.setCourse_id(rs.getInt("course_id"));
		c.setDays(rs.getString("days"));
		c.setDuration(rs.getDouble("duration"));
		c.setEndTime(rs.getString("endTime"));
		c.setRemark(rs.getString("remark"));
		c.setStartDate(rs.getDate("startDate").toLocalDate());
		c.setStartTime(rs.getString("startTime"));
		return c;
	}

	public List<ClassVO> find() {
		return find(0, null, 0);
	}

	public List<ClassVO> find(int courseId) {
		return find(courseId, null, 0);
	}

	public List<ClassVO> find(int courseId, LocalDate dateFrom, int fees) {
		StringBuilder sb = new StringBuilder("select * from class where 1 = 1 ");
		List<Object> params = new ArrayList<>();
		List<ClassVO> result = new ArrayList<>();
		
		if(courseId > 0) {
			sb.append("and course_id = ? ");
			params.add(courseId);
		}
		
		if(null != dateFrom) {
			sb.append("and startDate >= ? ");
			params.add(Date.valueOf(dateFrom));
		}
		
		if(fees > 0) {
			// TODO
		}
		
		try(Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			for(int i=0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Class clz = getObject(rs);
				result.add(getVO(clz));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
