package com.jdc.ishop.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.service.CategoryService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryList implements Initializable, Consumer<Category>{

	@FXML
	private TextField schName;

	@FXML
	private TableView<Category> table;
	
	private CategoryService service;

	@FXML
	void addNew(ActionEvent event) {
		CategoryEdit.showView(null, this);
	}

	@FXML
	void clear(ActionEvent event) {
		schName.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = CategoryService.getInstance();
		
		schName.textProperty().addListener((a,b,c) -> search());
		search();
	}

	@Override
	public void accept(Category t) {
		// save category
		service.save(t);
		// load table view
		search();
	}
	
	private void search() {
		// search 
		List<Category> list = service.find(schName.getText());
		table.getItems().clear();
		table.getItems().addAll(list);
	}

}
