package com.jdc.hotel.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Reservation implements Serializable {

	private static final long serialVersionUID = 1L;

	public Reservation() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private long id;

	private LocalDate checkIn;

	private LocalDate checkOut;

	private String remark;

	private String status;

	private double roomCharges;

	private double roomServiceCharges;

	private double laundryServiceCharges;

	private Security security;

	@ManyToOne
	private Customer contactPerson;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getRoomCharges() {
		return roomCharges;
	}

	public void setRoomCharges(double roomCharges) {
		this.roomCharges = roomCharges;
	}

	public double getRoomServiceCharges() {
		return roomServiceCharges;
	}

	public void setRoomServiceCharges(double roomServiceCharges) {
		this.roomServiceCharges = roomServiceCharges;
	}

	public double getLaundryServiceCharges() {
		return laundryServiceCharges;
	}

	public void setLaundryServiceCharges(double laundryServiceCharges) {
		this.laundryServiceCharges = laundryServiceCharges;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Customer getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(Customer contactPerson) {
		this.contactPerson = contactPerson;
	}

}