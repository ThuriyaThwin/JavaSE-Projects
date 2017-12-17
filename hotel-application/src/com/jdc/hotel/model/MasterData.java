package com.jdc.hotel.model;

import java.io.Serializable;

public class MasterData extends AbstractSecurityModel implements Serializable {
	
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


	private long id;

	private String tblName;

	private String colName;

	private String value;

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


}