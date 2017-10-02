package com.jdc.flag.game;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class QuestionGenerator {
	
	private static QuestionGenerator generator;
	private Properties prop;
	private List<String> flags;
	private List<String> names;
	
	public FlagGame getGame(int count) {
		Collections.shuffle(flags);
		return new FlagGame(flags.stream().limit(count).map(this::convert).collect(Collectors.toList()));
	}
	
	public static QuestionGenerator getGenerator() {
		if(null == generator) {
			generator = new QuestionGenerator();
		}
		return generator;
	}
	
	private Question convert(String name) {
		
		// Flag
		Flag flag = new Flag();
		flag.setImage(name);
		flag.setName(prop.getProperty(name.split("\\.")[0]));
		
		Collections.shuffle(names);
		
		List<String> questions = names.stream().filter(a -> !a.equals(flag.getName()))
				.limit(3).collect(Collectors.toList());
		
		questions.add(flag.getName());
		Collections.shuffle(questions);
		
		Question question = new Question();
		question.setFlag(flag);
		question.setNames(questions);
		
		return question;
	}
	
	private QuestionGenerator() {
		
		try {
			prop = new Properties();
			prop.load(getClass().getResourceAsStream("flags.properties"));
			names = prop.values().stream().map(a -> a.toString()).collect(Collectors.toList());
			
			flags = new ArrayList<>();
			
			File file = new File("flags");
			
			File [] files = file.listFiles();
			
			for(File f : files) {
				flags.add(f.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
