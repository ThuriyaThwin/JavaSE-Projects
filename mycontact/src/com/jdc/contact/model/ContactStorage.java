package com.jdc.contact.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jdc.contact.model.Contact.Group;

public class ContactStorage {
	
	private static ContactStorage instance;
	
	public static ContactStorage getInstance() {
		
		if(null == instance) {
			instance = new ContactStorage();
		}
		return instance;
	}
	
	private List<Contact> list;
	
	private ContactStorage() {
		list = new ArrayList<>();
	}
	
	public void add(Contact c) {
		list.add(c);
	}
	
	public List<Contact> getAll() {
		return list;
	}

	public List<Contact> search(Group group, String name, String phone) {
		Predicate<Contact> pred = a -> true;
		
		if(null != group) {
			pred = pred.and(a -> a.getGroup() == group);
		}
		
		if(null != name && !name.isEmpty()) {
			pred = pred.and(a -> a.getName().startsWith(name));
		}

		if(null != phone && !phone.isEmpty()) {
			pred = pred.and(a -> a.getPhone().startsWith(phone));
		}
		
		return list.stream().filter(pred).collect(Collectors.toList());
	}

}
