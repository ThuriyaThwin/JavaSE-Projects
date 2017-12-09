package com.jdc.hotel.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HotelService implements Serializable {

	private static final long serialVersionUID = 1L;

	public HotelService() {
	}

	@Id
	private long id;

	private LocalDateTime serviceTime;

	private double charges;

	private int count;

	public RoomReservation reservation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(LocalDateTime serviceTime) {
		this.serviceTime = serviceTime;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public RoomReservation getReservation() {
		return reservation;
	}

	public void setReservation(RoomReservation reservation) {
		this.reservation = reservation;
	}

}