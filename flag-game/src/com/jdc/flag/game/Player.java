package com.jdc.flag.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Player implements Serializable, Comparable<Player> {

	private String player;
	private String password;
	private int questionCount;

	private List<Result> results;

	private FlagGame currentGame;

	public void addResult(Result r) {
		results.add(r);
	}

	public Player() {
		results = new ArrayList<>();
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public FlagGame getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(FlagGame currentGame) {
		this.currentGame = currentGame;
	}

	public String getMaxGameCount() {
		Result max = getMaxResult();
		if (null != max) {
			return String.valueOf(max.totalProperty().get());
		}

		return "";
	}

	public String getMaxRight() {
		Result max = getMaxResult();
		if (null != max) {
			return String.valueOf(max.rightProperty().get());
		}

		return "";
	}

	public String getMaxPercent() {
		Result max = getMaxResult();
		if (null != max) {
			return String.valueOf(max.percentageProperty().get());
		}

		return "";
	}

	@Override
	public int compareTo(Player o) {
		Result thisMax = getMaxResult();
		Result otherMax = o.getMaxResult();

		if (null != thisMax && null != otherMax) {
			return otherMax.percentageProperty().get() - thisMax.percentageProperty().get();
		}

		if (null != thisMax) {
			return -1;
		}

		if (null != otherMax) {
			return 1;
		}

		return 0;
	}

	private Result getMaxResult() {
		return results.stream().sorted((a, b) -> b.percentageProperty().get() - a.percentageProperty().get())
				.findFirst().orElse(null);
	}

}
