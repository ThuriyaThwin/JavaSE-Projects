package com.jdc.ishop.controller;

import java.util.function.Consumer;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategoryEdit {

    @FXML
    private Label title;

    @FXML
    private TextField name;

    @FXML
    private CheckBox deleteFlag;
    
    private Category category;
    private Consumer<Category> saveHandler;

    @FXML
    void save(ActionEvent event) {
    		if(null == category) {
    			category = new Category();
    		}
    		
    		// TODO set category name
    		
    		// save
    		saveHandler.accept(category);
    		
    		// close
    		name.getScene().getWindow().hide();
    }
    
    @FXML
    void close(ActionEvent event) {
    		// close
    		name.getScene().getWindow().hide();
    }

	public static void showView(Category category, Consumer<Category> saveHandler) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(CategoryEdit.class.getResource("CategoryEdit.fxml"));
			Parent view = loader.load();
			CategoryEdit edit = loader.getController();
			edit.title.setText(null == category ? "Add New Category" : "Edit Category");
			edit.category = category;
			edit.saveHandler = saveHandler;
			
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(view));
			stage.show();
			
		} catch (Exception e) {
			throw new IShopException("Please contact to Dev Team.");
		}
		
	}

}
