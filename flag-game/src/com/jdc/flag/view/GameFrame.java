package com.jdc.flag.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.flag.game.FlagGame;
import com.jdc.flag.game.GameController;
import com.jdc.flag.game.Player;
import com.jdc.flag.game.PlayerService;
import com.jdc.flag.game.QuestionGenerator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GameFrame implements Initializable, GameController{
	
	private static GameFrame controller;

    @FXML
    private Label player;

    @FXML
    private Label total;

    @FXML
    private Label right;

    @FXML
    private Label wrong;

    @FXML
    private StackPane contentView;
    
    private FlagGame currentGame;
    private Player currentPlayer;

    @FXML
    void exit(ActionEvent event) {
    	PlayerService.getService().save();
    	Platform.exit();
    }

    @FXML
    void save(ActionEvent event) {
    	currentPlayer.setCurrentGame(currentGame);
    	PlayerService.getService().save();
    }

    @FXML
    void start(ActionEvent event) {
    	
    	// no question count
    	if(currentPlayer.getQuestionCount() == 0) {
    		setting(null);
    		return;
    	}

    	// there is current game
    	if(currentPlayer.getCurrentGame() != null) {
    		setCurrentGame(currentPlayer.getCurrentGame());
    		// show current game view
    		start();
    		return;
    	}
    	
    	// new game
    	setCurrentGame(QuestionGenerator.getGenerator().getGame(currentPlayer.getQuestionCount()));
    	start();
    }
    
    private void setCurrentGame(FlagGame currentGame) {
    	this.currentGame = currentGame;
    	currentGame.getResult().totalProperty().addListener((a,b,c) -> total.setText(String.valueOf(c)));
       	currentGame.getResult().rightProperty().addListener((a,b,c) -> right.setText(String.valueOf(c)));
       	currentGame.getResult().wrongProperty().addListener((a,b,c) -> wrong.setText(String.valueOf(c)));
       	
       	total.setText(currentGame.getResult().totalProperty().getValue().toString());
       	right.setText(currentGame.getResult().rightProperty().getValue().toString());
       	wrong.setText(currentGame.getResult().wrongProperty().getValue().toString());
	}

	public void start() {
    	Parent view = GameView.getView(currentGame);
    	contentView.getChildren().clear();
    	contentView.getChildren().add(view);
    }
    
    @FXML
    void setting(ActionEvent event) {
    	Parent view = Setting.getView(this);
    	contentView.getChildren().clear();
    	contentView.getChildren().add(view);
    }

    @FXML
    void home(ActionEvent event) {
    	Parent view = GameHome.getView();
    	contentView.getChildren().clear();
    	contentView.getChildren().add(view);
    }

    public static HBox getView(Player player) {
		try {
			FXMLLoader loader = new FXMLLoader(Login.class.getResource("GameFrame.fxml"));
			HBox box = loader.load();
			
			GameFrame frame = loader.getController();
			frame.setPlayer(player);
			return box;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setPlayer(Player currentPlayer) {

		this.currentPlayer = currentPlayer;
		player.setText(currentPlayer.getPlayer());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = this;
		home(null);
	}
	
	public static GameFrame getController() {
		return controller;
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
}
