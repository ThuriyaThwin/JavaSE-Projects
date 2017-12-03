package com.jdc.contact.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jdc.contact.model.Contact.Group;

public abstract class ContactRepo {

	private static ContactRepo instance;

	public static ContactRepo getInstance() {
		
		if(null == instance) {
			instance = new ContactRepoObject();
		}
		return instance;
	}

	protected List<Contact> list;

	public ContactRepo() {
		super();
	}

	public void add(Contact c) {
		
		if(list.size() > 0) {
			Contact last = list.get(list.size() -1);
			c.setId(last.getId() + 1);
		} else {
			c.setId(1);
		}
		
		list.add(c);
	}

	public List<Contact> getAll() {
		return new ArrayList<>(list);
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