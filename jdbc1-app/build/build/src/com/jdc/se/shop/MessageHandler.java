package com.jdc.se.shop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageHandler {
	
	@FXML
	private Label message;

	public static void handle(ShopException e) {
		try {
			
			FXMLLoader loader = new FXMLLoader(MessageHandler.class.getResource("Message.fxml"));
			Parent view = loader.load();
			
			MessageHandler controller = loader.getController();
			controller.message.setText(e.getMessage());
			
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(new Scene(view));
			
			stage.show();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void close() {
		message.getScene().getWindow().hide();
	}
}
