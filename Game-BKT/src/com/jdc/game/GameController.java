package com.jdc.game;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class GameController implements Initializable {
	
	@FXML
	private ImageView systemImage;
	@FXML
	private ImageView yourImage;
	@FXML
	private HBox controleBtnPane;
	@FXML
	private Label totalCount;
	@FXML
	private Label yourMark;
	@FXML
	private Label systemMark;
	@FXML
	private Label drawCount;
	@FXML
	private Label result;
	
	private GameResult gameResult;
	
	private Image defaultImage;
	
	private CharacterAnimation yourAnimation;
	private CharacterAnimation systemAnimation;
	private CharacterAnimation resultAnimation;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		AudioClip audio = new AudioClip(new File("music.wav").toURI().toString());
		audio.setCycleCount(AudioClip.INDEFINITE);
		audio.play();
		
		gameResult = new GameResult();
		
		SideAnimation ani1 = new SideAnimation(systemImage);
		SideAnimation ani2 = new SideAnimation(yourImage);
		
		ani1.play();
		ani2.play();
		
		defaultImage = new Image(this.getClass().getResourceAsStream("gun.png"));
		
		yourAnimation = new CharacterAnimation(yourImage);
		systemAnimation = new CharacterAnimation(systemImage);
		resultAnimation = new CharacterAnimation(result);
		
		totalCount.textProperty().bind(gameResult.total());
		yourMark.textProperty().bind(gameResult.yuor());
		systemMark.textProperty().bind(gameResult.system());
		drawCount.textProperty().bind(gameResult.draw());
		
		List<GameItemButton> buttons = GameModel.getModel().getItems()
				.stream().map(item -> {
					return new GameItemButton(item, this::playGame);
				}).collect(Collectors.toList());
		
		controleBtnPane.getChildren().addAll(buttons);
		
		reset();
	}
	
	public void playGame(GameItem yourItem) {
		
		GameItem systemItem = GameModel.getModel().getItemRandom();
		Image sImage = new Image(this.getClass().getResourceAsStream(systemItem.getImage()));
		Image yImage = new Image(this.getClass().getResourceAsStream(yourItem.getImage()));
		
		Transition trans = null;
		String voice = null;
		
		int result = yourItem.compareTo(systemItem);
		gameResult.increaseTotal();
		voice = yourItem.getVoice();
		
		if(result > 0) {
			// win
			this.result.setText("You Win!");
			gameResult.increaseYour();
			
			SequentialTransition ani = new SequentialTransition(
					systemAnimation.getAnimation(sImage),
					new PauseTransition(Duration.millis(50)),
					yourAnimation.getAnimation(yImage),
					new PauseTransition(Duration.millis(50)),
					resultAnimation.getAnimation(null)
			);
			
			trans = ani;
			
		} else if (result < 0) {
			// loose
			this.result.setText("You Loose!");
			gameResult.increaseSystem();

			SequentialTransition ani = new SequentialTransition(
					yourAnimation.getAnimation(yImage),
					new PauseTransition(Duration.millis(50)),
					systemAnimation.getAnimation(sImage),
					new PauseTransition(Duration.millis(50)),
					resultAnimation.getAnimation(null)
			);
			
			voice = systemItem.getVoice();
			trans = ani;
		} else {
			// draw
			this.result.setText("Draw!");
			gameResult.increaseDraw();
			
			ParallelTransition ani = new ParallelTransition(
					yourAnimation.getAnimation(yImage),
					systemAnimation.getAnimation(sImage),
					resultAnimation.getAnimation(null)
					);

			trans = ani;
		}
		
		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		AudioClip audio = new AudioClip(voice);
		
		trans.setOnFinished(e -> {
			audio.play();
		});
		
		trans.play();
	}
	
	public void reset() {
		
		result.setText("");
		
		// set display images
		systemImage.setImage(defaultImage);
		yourImage.setImage(defaultImage);
		
		// reset result
		gameResult.init();
	}

}
