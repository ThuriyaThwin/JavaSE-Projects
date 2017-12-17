package com.jdc.hotel.application.modal;

import java.util.function.Consumer;

import com.jdc.hotel.model.RoomType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditRoomTypes {
	@FXML
	private Label title;
	
    @FXML
    private TextField name;

    @FXML
    private TextField bedCount;

    @FXML
    private TextField roomCharges;
	
	
	private RoomType oldData;
	private Consumer<RoomType> saveHandler;

	public void close() {
		title.getScene().getWindow().hide();
	}

	public void save() {
		RoomType type = getViewData();
		saveHandler.accept(type);
		close();
	}

	private RoomType getViewData() {
		if(null == oldData) {
			oldData = new RoomType();
		}
		
		oldData.setBedCount(Integer.parseInt(bedCount.getText()));
		oldData.setPrice(Double.parseDouble(roomCharges.getText()));
		oldData.setName(name.getText());
		
		return oldData;
	}

	public static void showView(Consumer<RoomType> saveHandler, RoomType data) {
		try {

			Stage stage = new Stage(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader(EditRoomTypes.class.getResource("EditRoomType.fxml"));
			Parent root = loader.load();
			
			EditRoomTypes cont = loader.getController();
			cont.saveHandler = saveHandler;
			cont.oldData = data;
			if(null != data) {
				cont.name.setText(data.getName());
				cont.roomCharges.setText(String.valueOf(data.getPrice()));
				cont.bedCount.setText(String.valueOf(data.getBedCount()));
			}
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
