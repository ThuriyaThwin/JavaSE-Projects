package com.jdc.hotel.model;

import java.io.Serializable;
import java.util.List;

public class RoomReservation implements Serializable {

	private static final long serialVersionUID = 1L;

	public RoomReservation() {
	}

	private long id;

	private Room room;

	private Reservation reservation;

	private List<Customer> customers;

	private Security security;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}