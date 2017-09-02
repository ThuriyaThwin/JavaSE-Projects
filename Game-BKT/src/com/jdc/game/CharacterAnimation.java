package com.jdc.game;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CharacterAnimation {

	private Node node;

	public CharacterAnimation(Node node) {
		super();
		this.node = node;
	}

	public Transition getAnimation(Image image) {
		
		// size down
		ScaleTransition transDown1 = new ScaleTransition(Duration.millis(300), node);
		transDown1.setFromX(1);
		transDown1.setFromY(1);
		transDown1.setToX(0.1);
		transDown1.setToY(0.1);
		transDown1.setCycleCount(1);
		transDown1.setAutoReverse(false);
		
		// change image
		if(image != null) {
			ImageView imv = (ImageView) node;
			transDown1.setOnFinished(event -> imv.setImage(image));
		}
		
		// size up
		ScaleTransition transUp = new ScaleTransition(Duration.millis(350), node);
		transUp.setFromX(0.1);
		transUp.setFromY(0.1);
		transUp.setToX(1.2);
		transUp.setToY(1.2);
		transUp.setCycleCount(1);
		transUp.setAutoReverse(false);
		
		// size down
		ScaleTransition transDown2 = new ScaleTransition(Duration.millis(50), node);
		transDown2.setFromX(1.2);
		transDown2.setFromY(1.2);
		transDown2.setToX(1);
		transDown2.setToY(1);
		transDown2.setCycleCount(1);
		transDown2.setAutoReverse(false);
		
		
		return new SequentialTransition(transDown1, transUp, transDown2);
	}

}
