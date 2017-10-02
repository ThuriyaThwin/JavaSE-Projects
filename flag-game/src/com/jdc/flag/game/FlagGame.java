package com.jdc.flag.game;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FlagGame implements Serializable {

	private List<Question> questions;
	private Result result;
	private int current;

	public FlagGame(List<Question> questions) {
		super();
		this.questions = questions;
		result = new Result();
		result.totalProperty().set(questions.size());
	}

	public Question next() {

		if (questions.size() > current) {
			return questions.get(current ++);
		}

		return null;
	}
	
	public boolean check(String answer) {
		Question q = questions.get(current - 1);
		return result.setResult(q.getFlag().getName().equals(answer));
	}
	
	public Result getResult() {
		return result;
	}
	
	public int getCurrent() {
		return current;
	}
}
