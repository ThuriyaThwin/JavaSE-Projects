package com.jdc.trans;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class SampleController implements Initializable {

    private static final double TIME = 500;

	@FXML
    private Circle node;
	@FXML
	private Slider slider;
	@FXML
	private Slider speedSlider;
	
	@FXML
	private Label catchCount;
	@FXML
	private Label looseCount;
	@FXML
	private AnchorPane pane;
	
	private int catachCnt;
	private int looseCnt;
    
    private SequentialTransition trans;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		node.radiusProperty().bind(slider.valueProperty());

		
		// up
		TranslateTransition up = new TranslateTransition(Duration.millis(TIME), node);
		up.setFromY(0);
		up.setToY(-400);
		up.setAutoReverse(false);
		up.setCycleCount(1);
		

		// down
		TranslateTransition down = new TranslateTransition(Duration.millis(TIME), node);
		down.setFromY(-400);
		down.setToY(0);
		down.setAutoReverse(false);
		down.setCycleCount(1);
		
		trans = new SequentialTransition(up, down);
		trans.setCycleCount(Timeline.INDEFINITE);
		trans.setAutoReverse(false);
		trans.rateProperty().bind(speedSlider.valueProperty());
		
		pane.setOnMouseClicked(event -> {
			looseCount.setText(String.valueOf( ++ looseCnt));
		});
		
		node.setOnMouseClicked(event -> {
			-- looseCnt;
			if(trans.getStatus() == Status.RUNNING) {
				trans.pause();
				catchCount.setText(String.valueOf( ++ catachCnt));
			} else {
				trans.play();
			}
		});
		
	}

}
