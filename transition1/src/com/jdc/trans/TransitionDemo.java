package com.jdc.trans;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionDemo implements Initializable{
	
	@FXML
	private Rectangle node;
	
	@FXML
	private Label mode;
	@FXML
	private VBox transitionNames;
	
	private Transition transition;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transition = new SequentialTransition();
	}
	
	public void changeMode(ActionEvent event) {
		Button button = (Button) event.getSource();
		mode.setText(button.getText());
		boolean isPlaying = false;
		
		if(transition != null && transition.getStatus() == Status.RUNNING) {
			transition.stop();
			isPlaying = true;
		} 
		
		transition = mode.getText().equals("SEQ") ? new SequentialTransition() :
			new ParallelTransition();
		
		transition.setAutoReverse(false);
		transition.setCycleCount(Timeline.INDEFINITE);
		
		addTransitions();
		
		if(isPlaying) {
			transition.play();
		}
	}
	
	private void addTransitions() {
		
		transitionNames.getChildren().stream()
			.map(node -> node.getId())
			.forEach(id -> {
				switch (id) {
				case "Rotate":
					RotateTransition trans1 = new RotateTransition(Duration.millis(500), node);
					trans1.setFromAngle(0);
					trans1.setToAngle(360);
					addTransition(trans1);
					break;
				case "Translate":
					TranslateTransition trans2 = new TranslateTransition(Duration.millis(500), node);
					trans2.setFromX(-100);
					trans2.setToX(100);					
					addTransition(trans2);
					break;
				case "Fade":
					FadeTransition trans3 = new FadeTransition(Duration.millis(500), node);
					trans3.setFromValue(0.4);
					trans3.setToValue(1);
					addTransition(trans3);
					break;
				case "Fill":
					FillTransition trans4 = new FillTransition(Duration.millis(500), node);
					trans4.setFromValue(Color.BLUE);
					trans4.setToValue(Color.RED);
					addTransition(trans4);
					break;

				default:
					break;
				}
			});
		
	}

	private void addTransition(Transition trans1) {

		if(transition instanceof SequentialTransition) {
			SequentialTransition seq = (SequentialTransition) transition;
			seq.getChildren().add(trans1);
		} else {
			ParallelTransition parallel = (ParallelTransition) transition;
			parallel.getChildren().add(trans1);
		}
			
			
	}

	public void addTransition(ActionEvent event) {
		Button button = (Button) event.getSource();
		String name = button.getText();
		
		Node n = transitionNames.getChildren().stream()
			.filter(node -> node.getId().equals(name))
			.findAny().orElse(null);
		
		if(null == n) {
			HBox box = new HBox();
			box.setId(name);
			Button delete = new Button("x");
			
			delete.setOnAction(e -> {
				transitionNames.getChildren().remove(box);
			});
			
			Label label = new Label(name);
			
			box.setSpacing(10);
			box.setAlignment(Pos.CENTER_LEFT);
			
			box.getChildren().addAll(delete, label);
			
			transitionNames.getChildren().add(box);
			
			addTransitions();
			
		}
	}
	
	public void handle(ActionEvent event) {
		Button button = (Button) event.getSource();
		if(button.getText().equals("Play")) {
			transition.play();
			button.setText("Pause");
		} else {
			transition.stop();
			button.setText("Play");
		}
	}
	
}
