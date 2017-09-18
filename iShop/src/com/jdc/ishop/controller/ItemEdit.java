package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.utils.ManagerMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ItemEdit implements Initializable {

    @FXML
    private Label title;

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField name;

    @FXML
    private TextField barcode;
    @FXML
    private TextField price;

    @FXML
    private CheckBox isSoldOut;
    
    private Item item;
    private Consumer<Item> saveHandler;
    
    private CategoryService service;

    @FXML
    void close(ActionEvent event) {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save(ActionEvent event) {
    	
    	try {
    		if(null == item) {
    			item = new Item();
    		}
    		
    		// set data
    		item.setName(name.getText());
    		item.setBarcode(barcode.getText());
    		item.setCategoryId(category.getValue().getId());
    		item.setPrice(Integer.parseInt(price.getText()));
    		item.setDelete(isSoldOut.isSelected());
    		
    		// save
    		saveHandler.accept(item);
    		
    		// close
    		name.getScene().getWindow().hide();   
    		
		} catch (NullPointerException e) {
			throw new IShopException("Please select Category!");
		} catch (NumberFormatException e) {
			throw new IShopException("Please enter Price with digit!");
		} catch (IShopException e) {
			ManagerMessage.getInstance().alert(e);
		}
    }

	public static void showView(Item item, Consumer<Item> saveHandler) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(ItemEdit.class.getResource("ItemEdit.fxml"));
			Parent view = loader.load();
			ItemEdit edit = loader.getController();
			edit.title.setText(null == item ? "Add New Item" : "Edit Item");
			edit.setItem(item);
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

	private void setItem(Item item) {
		this.item = item;
		
		if(null != item) {
			Category c = category.getItems().stream().filter(a -> a.getId() == item.getCategoryId())
					.findAny().orElse(null);
			category.setValue(c);
			
			name.setText(item.getName());
			barcode.setText(item.getBarcode());
			price.setText(String.valueOf(item.getPrice()));
			isSoldOut.setSelected(item.isDelete());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		service = CategoryService.getInstance();
		
		category.getItems().addAll(service.find(false));
		
	}
}
