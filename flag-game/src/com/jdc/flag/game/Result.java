package com.jdc.flag.game;

import java.io.Serializable;
import java.time.LocalDateTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

@SuppressWarnings("serial")
public class Result implements Serializable {

	private IntegerProperty totalProperty;
	private IntegerProperty rightProperty;
	private IntegerProperty wrongProperty;
	private IntegerProperty percentageProperty;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public Result() {
		totalProperty = new SimpleIntegerProperty(0);
		rightProperty = new SimpleIntegerProperty(0);

		wrongProperty = new SimpleIntegerProperty(0);

		percentageProperty = new SimpleIntegerProperty(0);
		
		totalProperty.addListener((a,b,c) -> {
			if(totalProperty.get() > 0) {
				percentageProperty.set(rightProperty.get() / totalProperty.get() * 100);
			}
		});
		
	}
	
	public boolean setResult(boolean result) {
		if(result) {
			rightProperty.setValue(rightProperty.get() + 1);
		} else {
			wrongProperty.setValue(rightProperty.get() + 1);
		}
		
		return result;
	}

	public IntegerProperty totalProperty() {
		return totalProperty;
	}

	public IntegerProperty rightProperty() {
		return rightProperty;
	}

	public IntegerProperty wrongProperty() {
		return wrongProperty;
	}

	public IntegerProperty percentageProperty() {
		return percentageProperty;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}
