package com.jdc.jdbc.helper;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class TableInfoHelper<T extends BaseEntity> {

	private Class<T> type;
	
	private Field idField;
	
	private List<Field> otherFields;

	public TableInfoHelper(Class<T> type) {
		super();
		this.type = type;
		otherFields = new ArrayList<>();
		
		for(Field f : type.getDeclaredFields()) {
			f.setAccessible(true);
			
			Id id = f.getAnnotation(Id.class);
			
			if(null != id) {
				idField = f;
			} else {
				otherFields.add(f);
			}
		}
		
	}
	
	public String getTable() {
		Table table = type.getAnnotation(Table.class);
		if(null != table) {
			return table.value();
		}
		
		return type.getSimpleName().toLowerCase();
	}

	public String getInsertString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < otherFields.size(); i++) {
			
			if(i > 0) {
				sb.append(", ");
			}
			
			String columnName = getColumnName(otherFields.get(i));
			sb.append(columnName);
		}
		return sb.toString();
	}

	private String getColumnName(Field field) {
		Column col = field.getAnnotation(Column.class);
		if(null != col) {
			return col.value();
		}
		return field.getName();
	}

	public String getInsertParamString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < otherFields.size(); i++) {
			
			if(i > 0) {
				sb.append(", ");
			}
			
			sb.append("?");
		}
		return sb.toString();
	}

	public String getUpdateSetString() {
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < otherFields.size(); i++) {
			
			if(i > 0) {
				sb.append(", ");
			}
			
			String columnName = getColumnName(otherFields.get(i));
			sb.append(String.format("%s = ?", columnName));
		}
		return sb.toString();
	}
	
	public String getUpdateWhereString() {
		return String.format("%s = ?", getColumnName(idField));
	}
	
	public List<Object> getInsertParams(T obj) {
		List<Object> list = new ArrayList<>();
		try {
			for(int i = 0; i < otherFields.size(); i++) {
				Object value = otherFields.get(i).get(obj);
				list.add(getParamObject(value));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public List<Object> getUpdateParams(T obj) {
		List<Object> list = new ArrayList<>();
		try {
			for(int i = 0; i < otherFields.size(); i++) {
				Object value = otherFields.get(i).get(obj);
				list.add(getParamObject(value));
			}
			
			list.add(idField.get(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public T getData(ResultSet rs) throws SQLException {
		
		try {
			T obj = type.newInstance();
			
			for(Field f : type.getDeclaredFields()) {
				f.setAccessible(true);
				Object value = rs.getObject(getColumnName(f));
				f.set(obj, getValueObject(value));
			}
			
			return obj;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Object getParamObject(Object obj) {
		if(obj instanceof LocalDate) {
			LocalDate d = (LocalDate) obj;
			return Date.valueOf(d);
		} else if (obj instanceof LocalDateTime) {
			LocalDateTime d = (LocalDateTime) obj;
			return Timestamp.valueOf(d);
		} else if (obj instanceof LocalTime) {
			LocalTime d = (LocalTime) obj;
			return Time.valueOf(d);
		} 
		
		return obj;
	}
	
	private Object getValueObject(Object obj) {
		
		if(obj instanceof Date) {
			Date d = (Date) obj;
			return d.toLocalDate();
		} else if (obj instanceof Timestamp) {
			Timestamp d = (Timestamp) obj;
			return d.toLocalDateTime();
		} else if (obj instanceof Time) {
			Time d = (Time) obj;
			return d.toLocalTime();
		} 
		
		return obj;
	}
}
