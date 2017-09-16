package com.jdc.ishop.controller;

import java.util.function.Consumer;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ItemEdit {

    @FXML
    private Label title;

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private CheckBox isSoldOut;
    
    private Item item;
    private Consumer<Item> saveHandler;

    @FXML
    void close(ActionEvent event) {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save(ActionEvent event) {
		if(null == item) {
			item = new Item();
		}
		
		// TODO set category name
		
		// save
		saveHandler.accept(item);
		
		// close
		name.getScene().getWindow().hide();   	
    }

	public static void showView(Item item, Consumer<Item> saveHandler) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(ItemEdit.class.getResource("ItemEdit.fxml"));
			Parent view = loader.load();
			ItemEdit edit = loader.getController();
			edit.title.setText(null == item ? "Add New Item" : "Edit Item");
			edit.item = item;
			edit.saveHandler = saveHandler;
			
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(view));
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IShopException("Please contact to Dev Team.");
		}
		
	}
}
