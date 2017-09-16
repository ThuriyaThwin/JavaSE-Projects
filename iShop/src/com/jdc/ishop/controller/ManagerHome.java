package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.ishop.IShopException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ManagerHome implements Initializable {

    @FXML
    private Label title;

    @FXML
    private StackPane contextView;

    @FXML
    private Label message;

    @FXML
    void close(MouseEvent event) {
    	Platform.exit();
    }

    @FXML
    void showCategories(MouseEvent event) {
    	loadView("Category Management", "CategoryList.fxml");
    }

    @FXML
    void showHome(MouseEvent event) {
    	loadView("Admin Home", "Home.fxml");
    }

    @FXML
    void showItems(MouseEvent event) {
    	loadView("Item Management", "ItemList.fxml");
    }

    @FXML
    void showMembers(MouseEvent event) {
    	loadView("Member Management", "MemberList.fxml");
    }

    @FXML
    void showReports(MouseEvent event) {
    	loadView("Sale Reports", "SaleReport.fxml");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// show home view
		loadView("Admin Home", "Home.fxml");
	}

	public void loadView(String title, String viewName) {
		clearMessage();
		
		try {
			
			// set title
			this.title.setText(title);
			
			// load view
			Parent view = FXMLLoader.load(getClass().getResource(viewName));
			
			// set to context view
			contextView.getChildren().clear();
			contextView.getChildren().add(view);
			
		} catch (IShopException e) {
			message.setText(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearMessage() {
		message.setText("");
	}

}
