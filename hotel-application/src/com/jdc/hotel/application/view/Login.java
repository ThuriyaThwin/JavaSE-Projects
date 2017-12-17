package com.jdc.hotel.application.view;

import com.jdc.hotel.client.MasterDataClient;
import com.jdc.hotel.client.RoomTypeClient;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
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

		CommonDataLoader loader = new CommonDataLoader();

		loader.setOnSucceeded(event -> {
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
		});
		
		loader.start();

	}

	public void receptionLogin() {

	}

	class CommonDataLoader extends Service<Void> {

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					MasterDataClient.getClient().init();
					RoomTypeClient.getClient().init();
					return null;
				}
			};
		}

	}

}
