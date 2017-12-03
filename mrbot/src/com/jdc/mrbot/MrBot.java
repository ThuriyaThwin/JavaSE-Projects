package com.jdc.mrbot;

import java.util.HashMap;
import java.util.Map;

public class MrBot {

	private Map<String, String> dictionary;
	
	private String lastQuestion;
	
	public MrBot() {
		dictionary = new HashMap<>();
	}
	
	public String ask(String question) {
		
		if(null == lastQuestion) {
			// asking
			String result = dictionary.get(question);
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
			
			dictionary.put(lastQuestion, question);
			lastQuestion = null;
			return "Thank you!";
		}
		
	}
}
