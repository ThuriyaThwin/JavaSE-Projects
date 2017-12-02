package com.jdc.contact.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ContactRepoFile extends ContactRepo{

	private static final String FILE = "contact.txt";

	ContactRepoFile() {
		
		list = new ArrayList<>();
		
		// read data from file
		try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			
			String line = null;
			
			while(null != (line = br.readLine())) {
				String [] array = line.split("\t");
				Contact c = new Contact(array);
				list.add(c);
			}
			
		} catch(Exception e) {
			System.err.println("First Time");
		}
	}
	
	@Override
	public void add(Contact c) {
		super.add(c);
		
		// save to file
		try(PrintWriter bw = new PrintWriter(new FileWriter(FILE))) {
			for(Contact cont : list) {
				bw.println(cont.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
