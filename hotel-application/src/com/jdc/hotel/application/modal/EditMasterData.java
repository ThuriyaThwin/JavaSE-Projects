package com.jdc.hotel.application.modal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditMasterData {

	@FXML
	private Label title;

	public void close() {
		title.getScene().getWindow().hide();
	}

	public void save() {

	}

	public static void showView() {
		try {

			Stage stage = new Stage(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(EditMasterData.class.getResource("EditMasterData.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
