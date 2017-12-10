package com.jdc.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jdc.contact.model.Contact;
import com.jdc.contact.model.ContactRepo;

public class SortDemo2 {

	public static void main(String[] args) {
		
		Comparator<Contact> comp = new Comparator<Contact>() {
			
			@Override
			public int compare(Contact o1, Contact o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		
		List<Contact> list = ContactRepo.getInstance().getAll();
		SortDemo1.show(list);
		
		Collections.sort(list, comp);
		System.out.println("=============================");
		
		SortDemo1.show(list);		
	}
}
