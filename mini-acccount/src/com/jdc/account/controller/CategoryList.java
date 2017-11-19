package com.jdc.account.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.jdc.account.entity.Category;
import com.jdc.account.service.CategoryService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class CategoryList implements Initializable{

    @FXML
    private TableView<Category> table;

    @Inject
    private CategoryService service;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.getItems().clear();
		table.getItems().addAll(service.getAllCategory());
	}

}
