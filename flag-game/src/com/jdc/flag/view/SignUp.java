package com.jdc.flag.view;

import com.jdc.flag.GameApplication;
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
    		// TODO sign up operation
    		GameApplication.showGameView();
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
