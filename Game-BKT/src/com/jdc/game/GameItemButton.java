package com.jdc.game;

import java.util.function.Consumer;

import javafx.animation.Animation.Status;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameItemButton extends VBox {

	public GameItemButton(GameItem item, Consumer<GameItem> listener) {
		super();

		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPrefWidth(100);
		Image image = new Image(this.getClass().getResourceAsStream(item.getImage())); 
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(80);
		imageView.setPreserveRatio(true);
		
		ScaleTransition transUp = new ScaleTransition(Duration.millis(50), imageView);
		transUp.setFromX(1);
		transUp.setFromY(1);
		transUp.setToX(1.2);
		transUp.setToY(1.2);
		transUp.setCycleCount(1);
		
		ScaleTransition transDown = new ScaleTransition(Duration.millis(50), imageView);
		transDown.setFromX(1.2);
		transDown.setFromY(1.2);
		transDown.setToX(1);
		transDown.setToY(1);
		transDown.setCycleCount(1);
		
		SequentialTransition trans = new SequentialTransition(transUp, transDown);
		trans.setCycleCount(3);
		trans.setAutoReverse(false);

		setOnMouseEntered(event -> {
			if(trans.getStatus() != Status.RUNNING) {
				trans.play();
			}
		});
		
		Label label = new Label(item.getName());
		
		getChildren().addAll(imageView, label);
		
		setOnMouseClicked(event -> listener.accept(item));
	}
	
	
}
