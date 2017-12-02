package com.jdc.contact.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactRepoObject extends ContactRepo{
	
	private static final String FILE = "contact.object";

	@SuppressWarnings("unchecked")
	ContactRepoObject() {
	
		// read data from file
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			
			list = (List<Contact>) in.readObject();

		} catch (Exception e) {
			System.err.println("First Time");
			e.printStackTrace();
		} 
		
		if(list == null) {
			list = new ArrayList<>();
		}
	
	}
	
	@Override
	public void add(Contact c) {
		super.add(c);
		
		// save to file
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
