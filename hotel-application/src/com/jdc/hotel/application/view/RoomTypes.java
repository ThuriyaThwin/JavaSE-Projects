package com.jdc.hotel.application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.hotel.application.modal.EditRoomTypes;
import com.jdc.hotel.client.RoomTypeClient;
import com.jdc.hotel.model.RoomType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

public class RoomTypes implements Initializable{

    @FXML
    private CheckBox validOnly;

    @FXML
    private TableView<RoomType> table;

    @FXML
    void addNew(ActionEvent event) {
    		EditRoomTypes.showView(this::save, null);
    }
    
    private void save(RoomType type) {
    		if(type.getId() > 0) {
    			RoomTypeClient.getClient().update(type);
    		} else {
    			RoomTypeClient.getClient().create(type);
    		}
    		
    		search(null);
    }

    @FXML
    void search(ActionEvent event) {
    		List<RoomType> list = RoomTypeClient.getClient().search(validOnly.isSelected());
    		table.getItems().clear();
    		table.getItems().addAll(list);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(event -> {
			RoomType type = table.getSelectionModel().getSelectedItem();
			EditRoomTypes.showView(this::save, type);
		});
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(event -> {
			RoomType type = table.getSelectionModel().getSelectedItem();
			type.getSecurity().setDelFlag(true);
			RoomTypeClient.getClient().update(type);
			search(null);
		});
		
		table.setContextMenu(new ContextMenu(edit, delete));
		
		search(null);
	}

}
