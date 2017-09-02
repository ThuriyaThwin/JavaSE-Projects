package com.jdc.game;

import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class SideAnimation {
	
	private SequentialTransition trans;
	
	public SideAnimation(Node node) {
		super();
		
		TranslateTransition left = new TranslateTransition(Duration.millis(1000), node);
		left.setFromX(10);
		left.setToX(- 10);
		TranslateTransition right = new TranslateTransition(Duration.millis(1000), node);
		right.setFromX(-10);
		right.setToX(10);
		
		trans = new SequentialTransition(left, right);
		trans.setCycleCount(Timeline.INDEFINITE);
		trans.setAutoReverse(false);
	}
	
	public void play() {
		if(trans.getStatus() != Status.RUNNING) {
			trans.play();
		}
	}
	
	public void pause() {
		if(trans.getStatus() == Status.RUNNING) {
			trans.pause();
		}
	}

}
