package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ItemList implements Initializable, Consumer<Item>{

    @FXML
    private ComboBox<Category> schCategory;

    @FXML
    private TextField priceFrom;

    @FXML
    private TextField priceTo;

    @FXML
    private TextField schName;

    @FXML
    private TableView<Item> table;

    @FXML
    void addNew(ActionEvent event) {
    	ItemEdit.showView(null, this);
    }

    @FXML
    void clear(ActionEvent event) {
    	schCategory.setValue(null);
    	priceFrom.clear();
    	priceTo.clear();
    	schName.clear();
    }

	@Override
	public void accept(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
