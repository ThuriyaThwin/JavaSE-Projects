package com.jdc.hotel.application.modal;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.hotel.model.MasterData;
import com.jdc.hotel.model.MasterData.Table;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditMasterData implements Initializable {

	@FXML
	private Label title;
	
	private Consumer<MasterData> saveHandler;
	private MasterData oldData;
	
	@FXML
	private ComboBox<Table> table;
	@FXML
	private ComboBox<String> columns;
	@FXML
	private TextField value;

	public void close() {
		title.getScene().getWindow().hide();
	}

	public void save() {
		MasterData data = getViewData();
		
		saveHandler.accept(data);
		
		close();
	}

	private MasterData getViewData() {
		if(oldData == null) {
			oldData = new MasterData();
		}
		
		oldData.setTblName(table.getValue().toString());
		oldData.setColName(columns.getValue());
		oldData.setValue(value.getText());
		
		return oldData;
	}

	public static void showView(Consumer<MasterData> saveHandler, MasterData data) {
		try {

			Stage stage = new Stage(StageStyle.UNDECORATED);
			FXMLLoader loader = new FXMLLoader(EditMasterData.class.getResource("EditMasterData.fxml"));
			Parent root = loader.load();
			
			EditMasterData controller = loader.getController();
			controller.saveHandler = saveHandler;
			controller.oldData = data;
			
			controller.title.setText(data == null ? "Create New Data" : "Update Data");
			
			stage.setScene(new Scene(root));
			
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.getItems().addAll(Table.values());
		table.valueProperty().addListener((a,b,c) -> {
			columns.getItems().clear();
			columns.getItems().addAll(table.getValue().getColumns());
		});
		
		table.setValue(Table.Customer);
	}

}
