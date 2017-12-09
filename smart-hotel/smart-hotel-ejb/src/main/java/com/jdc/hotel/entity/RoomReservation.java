package com.jdc.hotel.entity;

import java.util.*;

public class RoomReservation {

	public RoomReservation() {
	}

	private long id;

	private Room room;

	private Reservation reservation;

	private Set<Customer> customers;

	private Security security;

}