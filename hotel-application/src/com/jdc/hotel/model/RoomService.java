package com.jdc.hotel.model;

public class RoomService extends HotelService {

	private static final long serialVersionUID = 1L;

	private RoomServiceItem item;

	public RoomServiceItem getItem() {
		return item;
	}

	public void setItem(RoomServiceItem item) {
		this.item = item;
	}

}