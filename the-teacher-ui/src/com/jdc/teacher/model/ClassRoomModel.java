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

public class ClassRoomModel {

	public void save(ClassRoom room) {
		
		String sql = "insert into class_room values (?, ?)";
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql )){
			// check already exit
			List<ClassRoom> rooms = find(room.getGrade(), room.getYear());
			
			if(!rooms.isEmpty()) {
				throw new TeacherApplicationException("This room is already exit!");
			}
			
			// save
			stmt.setString(1, room.getGrade().name());
			stmt.setString(2, room.getYear());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
	}

	public List<ClassRoom> find(Grade grade, String year) {
		
		List<ClassRoom> rooms = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder("select * from class_room where 1 = 1");
		List<Object> params = new ArrayList<>();
		
		if(null != grade) {
			sb.append(" and grade = ?");
			params.add(grade);
		}
		
		if(null != year && !year.isEmpty()) {
			sb.append(" and year = ?");
			params.add(year);
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
				ClassRoom room = getData(rs);
				rooms.add(room);
			}

		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
		
		
		return rooms;
	}

	private ClassRoom getData(ResultSet rs) throws SQLException {
		ClassRoom room = new ClassRoom();
		room.setGrade(Grade.valueOf(rs.getString("grade")));
		room.setYear(rs.getString("year"));
		return room;
	}

	public List<String> findYearByGrade(Grade value) {
		List<String> result = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder("select distinct year from class_room where 1 =1");
		if(null != value) {
			sb.append(" and grade = ?");
		}
		
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
			
			if(null != value) {
				stmt.setString(1, value.name());
			}
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(rs.getString("year"));
			}
			
		} catch (SQLException e) {
			throw new TeacherApplicationException("There is database operation error.");
		}
		
		return result;
	}

}
