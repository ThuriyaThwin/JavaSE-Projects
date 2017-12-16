package com.jdc.hotel.model;

public class LaundryService extends HotelService {

	private static final long serialVersionUID = 1L;

	private LaundryItem item;

	public LaundryItem getItem() {
		return item;
	}

	public void setItem(LaundryItem item) {
		this.item = item;
	}

}