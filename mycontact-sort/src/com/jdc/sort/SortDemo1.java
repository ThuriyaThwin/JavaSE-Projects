package com.jdc.sort;

import java.util.Collections;
import java.util.List;

import com.jdc.contact.model.Contact;
import com.jdc.contact.model.ContactRepo;

public class SortDemo1 {

	public static void main(String[] args) {
		
		List<Contact> list = ContactRepo.getInstance().getAll();
		show(list);
		
		Collections.sort(list);
		System.out.println("=============================");
		show(list);
	}
	
	public static void show(List<Contact> list) {
		String format = "%3d %-15s %-10s %s%n";
		for (Contact c : list) {
			System.out.printf(format, c.getId(), c.getName(), c.getGroup(), c.getPhone());
		}
	}
}
