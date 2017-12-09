package com.jdc.hotel.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public abstract class HotelService implements Serializable {

	private static final long serialVersionUID = 1L;

	public HotelService() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	private LocalDateTime serviceTime;

	private double charges;

	private int count;

	@ManyToOne
	private RoomReservation reservation;

	private Security security;

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

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