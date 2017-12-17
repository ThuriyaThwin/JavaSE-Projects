package com.jdc.hotel.application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.hotel.application.modal.EditMasterData;
import com.jdc.hotel.application.modal.EditRoomTypes;
import com.jdc.hotel.client.MasterDataClient;
import com.jdc.hotel.client.RoomTypeClient;
import com.jdc.hotel.model.MasterData;
import com.jdc.hotel.model.RoomType;
import com.jdc.hotel.model.MasterData.Table;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

public class MasterDataManagement implements Initializable {

	@FXML
	private ComboBox<Table> combo;

	@FXML
	private CheckBox status;

	@FXML
	private TableView<MasterData> table;

	public void search() {
		List<MasterData> list = MasterDataClient.getClient().search(combo.getValue(), status.isSelected());
		table.getItems().clear();
		table.getItems().addAll(list);
	}

	public void addNew() {
		EditMasterData.showView(this::save, null);
	}

	private void save(MasterData data) {
		if (data.getId() > 0) {
			MasterDataClient.getClient().update(data);
		} else {
			MasterDataClient.getClient().create(data);
		}
		combo.setValue(null);
		status.setSelected(false);
		search();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		combo.getItems().addAll(Table.values());

		combo.valueProperty().addListener((a, b, c) -> search());
		status.selectedProperty().addListener((a, b, c) -> search());
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(event -> {
			MasterData type = table.getSelectionModel().getSelectedItem();
			EditMasterData.showView(this::save, type);
		});
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(event -> {
			MasterData type = table.getSelectionModel().getSelectedItem();
			type.getSecurity().setDelFlag(true);
			MasterDataClient.getClient().update(type);
			search();
		});

		table.setContextMenu(new ContextMenu(edit, delete));

		search();
	}
}
