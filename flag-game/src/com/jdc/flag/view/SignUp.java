package com.jdc.flag.view;

import com.jdc.flag.GameApplication;
import com.jdc.flag.game.Player;
import com.jdc.flag.game.PlayerService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class SignUp {

	@FXML
	private JFXTextField player;

	@FXML
	private JFXPasswordField password;

	@FXML
	void login(ActionEvent event) {
		GameApplication.showLoginView();
	}

	@FXML
	void signup(ActionEvent event) {
		try {
			
			Player player = new Player();
			player.setPlayer(this.player.getText());
			player.setPassword(password.getText());
			
			PlayerService.getService().signUp(player);
			
			GameApplication.showGameView(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HBox getView() {
		try {
			HBox box = FXMLLoader.load(Login.class.getResource("SignUp.fxml"));
			return box;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
