package com.jdc.flag.game;

public interface GameController {
	Player getCurrentPlayer();
	FlagGame getCurrentGame();
	void start();
	void checkAnswer(String answer);
	void showResult();
}
