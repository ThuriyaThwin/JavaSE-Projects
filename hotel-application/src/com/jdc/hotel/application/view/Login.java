package com.jdc.hotel.application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login {

	@FXML
	private Label message;

	@FXML
	private TextField loginId;

	@FXML
	private PasswordField password;

	public void close() {
		message.getScene().getWindow().hide();
	}

	public void managerLogin() {
		try {
			// login operation
			
			// show manager view
			Stage stage = new Stage(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receptionLogin() {

	}

}
