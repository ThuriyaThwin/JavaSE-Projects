package com.jdc.mrbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MrBot implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<String, String> dictionary;
	
	private String lastQuestion;
	
	private static final File FILE;
	
	static {
		System.out.println(System.getProperty("user.home"));
		File home = new File(System.getProperty("user.home"));
		FILE = new File(home, ".MrBot");
	}
	
	private MrBot() {
		dictionary = new HashMap<>();
	}
	
	public String ask(String question) {
		
		if(null == lastQuestion) {
			// asking
			String result = dictionary.get(question.trim().toLowerCase());
			if(null == result) {
				// no answer
				lastQuestion = question;
				return "I don't know how to answer! Please teach me!";
			} else {
				// can answer
				return result;
			}
		} else {
			// teaching
			if(question.trim().isEmpty()) {
				return "I said teach me!!!! m(_0_)m";
			}
			
			dictionary.put(lastQuestion.trim().toLowerCase(), question);
			lastQuestion = null;
			return "Thank you!";
		}
		
	}
	
	private static MrBot BOT;
	
	public static MrBot getInstance() {
		
		if(null == BOT) {
			
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
				BOT = (MrBot) in.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(null == BOT) {
				BOT = new MrBot();
			}
			
		}
		
		return BOT;
	}
	
	public static void close() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(BOT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
