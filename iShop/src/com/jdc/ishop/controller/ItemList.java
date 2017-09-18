package com.jdc.ishop.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.model.service.ItemService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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
    
    private ItemService service;
    private CategoryService catService;

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
		service.save(t);
		
		search();
	}

	private void search() {
		List<Item> list = service.find(schCategory.getValue(), priceFrom.getText(), priceTo.getText(), schName.getText());
		table.getItems().clear();
		table.getItems().addAll(list);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = ItemService.getInstance();
		catService = CategoryService.getInstance();
		
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(e -> {
			Item item = table.getSelectionModel().getSelectedItem();
			ItemEdit.showView(item, this);
		});
		table.setContextMenu(new ContextMenu(edit));
		
		// add category data
		schCategory.getItems().addAll(catService.find(false));
		
		// add search listener
		schCategory.valueProperty().addListener((a,b,c) -> search());
		priceFrom.textProperty().addListener((a,b,c) -> search());
		priceTo.textProperty().addListener((a,b,c) -> search());
		schName.textProperty().addListener((a,b,c) -> search());
		
		search();
	}

}
