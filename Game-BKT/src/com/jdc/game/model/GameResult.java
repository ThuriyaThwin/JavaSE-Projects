package com.jdc.game.model;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameResult {
	
	private IntegerProperty totalProperty;
	private IntegerProperty yourProperty;
	private IntegerProperty systemProperty;
	private IntegerProperty drawProperty;
	
	public GameResult() {
		totalProperty = new SimpleIntegerProperty();
		yourProperty = new SimpleIntegerProperty();
		systemProperty = new SimpleIntegerProperty();
		drawProperty = new SimpleIntegerProperty();
	}
	
	public StringBinding total() {
		return totalProperty.asString();
	}

	public StringBinding yuor() {
		return yourProperty.asString();
	}
	
	public StringBinding system() {
		return systemProperty.asString();
	}
	
	public StringBinding draw() {
		return drawProperty.asString();
	}
	
	public void init() {
		totalProperty.set(0);
		yourProperty.set(0);
		systemProperty.set(0);
		drawProperty.set(0);
	}
	
	public void increaseTotal() {
		int data = totalProperty.get();
		totalProperty.set(data + 1);
	}
	
	public void increaseYour() {
		int data = yourProperty.get();
		yourProperty.set(data + 1);
	}
	public void increaseSystem() {
		int data = systemProperty.get();
		systemProperty.set(data + 1);
	}
	public void increaseDraw() {
		int data = drawProperty.get();
		drawProperty.set(data + 1);
	}
	
}
