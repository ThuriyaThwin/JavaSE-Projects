package com.jdc.hotel.model;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import org.codehaus.jackson.annotate.JsonIgnore;

public class MasterData implements Serializable {
	
	public enum Table {
		Customer(new String[] {"nationality"}), Reservation(new String[] {"status"});
		
		private String [] columns;

		private Table(String[] columns) {
			this.columns = columns;
		}
		
		public String[] getColumns() {
			return columns;
		}
	}

	private static final long serialVersionUID = 1L;

	public MasterData() {
		security = new Security();
	}

	private long id;

	private String tblName;

	private String colName;

	private String value;

	private Security security;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTblName() {
		return tblName;
	}

	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
	
	@JsonIgnore
	public String getStatus() {
		return security.isDelFlag() ? "Delete" : "Valid";
	}
	
	@JsonIgnore
	public String getCreation() {
		return security.getCreation() == null ? "" : 
			security.getCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	@JsonIgnore
	public String getUpdateTime() {
		return security.getModification() == null ? "" : 
			security.getModification().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	@JsonIgnore
	public String getCreateUser() {
		return security.getCreateUser();
	}

	@JsonIgnore
	public String getUpdateUser() {
		return security.getCreateUser();
	}
}