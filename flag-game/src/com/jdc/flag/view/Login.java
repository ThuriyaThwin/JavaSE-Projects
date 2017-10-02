package com.jdc.flag.view;

import com.jdc.flag.GameApplication;
import com.jdc.flag.game.GameException;
import com.jdc.flag.game.Player;
import com.jdc.flag.game.PlayerService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class Login {

	@FXML
	private JFXTextField player;

	@FXML
	private JFXPasswordField password;

	@FXML
	void login(ActionEvent event) {
		try {
			
			Player player = PlayerService.getService().login(this.player.getText(), password.getText());
			
			GameApplication.showGameView(player);
		} catch (GameException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void signUp(ActionEvent event) {
		GameApplication.showSignView();
	}

	public static HBox getView() {

		try {
			HBox box = FXMLLoader.load(Login.class.getResource("Login.fxml"));
			return box;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
