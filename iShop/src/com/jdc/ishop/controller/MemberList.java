package com.jdc.ishop.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Member;
import com.jdc.ishop.model.entity.Member.Role;
import com.jdc.ishop.model.service.MemberService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemberList implements Initializable, Consumer<Member> {

	@FXML
	private ComboBox<Role> schRole;

	@FXML
	private TextField schName;

	@FXML
	private TableView<Member> table;

	private MemberService service;

	@FXML
	void addNew(ActionEvent event) {
		MemberEdit.showView(null, this);
	}

	@FXML
	void clear(ActionEvent event) {
		schRole.setValue(null);
		schName.clear();
	}

	@Override
	public void accept(Member t) {
		service.save(t);
		search();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		service = MemberService.getInstance();

		schRole.getItems().addAll(Role.values());

		schRole.valueProperty().addListener((a, b, c) -> search());
		schName.textProperty().addListener((a, b, c) -> search());

		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(event -> MemberEdit.showView(table.getSelectionModel().getSelectedItem(), MemberList.this));

		table.setContextMenu(new ContextMenu(edit));

		search();
	}

	private void search() {
		List<Member> list = service.find(schRole.getValue(), schName.getText());
		table.getItems().clear();
		table.getItems().addAll(list);
	}

}
