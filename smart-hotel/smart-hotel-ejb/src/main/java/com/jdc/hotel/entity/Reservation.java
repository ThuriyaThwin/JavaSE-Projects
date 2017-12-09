package com.jdc.hotel.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {

	public Reservation() {
	}

	private long id;

	private LocalDate checkIn;

	private LocalDate checkOut;

	private String remark;

	private String status;

	private double roomCharges;

	private double roomServiceCharges;

	private double laundryServiceCharges;

	private Security security;

	private Customer contactPerson;

}