package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.ishop.IShopException;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
			FadeTransition fadeIn = new FadeTransition(Duration.millis(200), this.title);
			fadeIn.setFromValue(1.0);
			fadeIn.setToValue(0);
			fadeIn.setAutoReverse(false);
			fadeIn.setCycleCount(1);
			
			fadeIn.setOnFinished(event -> this.title.setText(title));
			
			FadeTransition fadeOut = new FadeTransition(Duration.millis(600), this.title);
			fadeOut.setFromValue(0);
			fadeOut.setToValue(1.0);
			fadeOut.setAutoReverse(false);
			fadeOut.setCycleCount(1);
			
			SequentialTransition titleTrans = new SequentialTransition(fadeIn, fadeOut);
			titleTrans.play();
			
			// load view
			Parent view = FXMLLoader.load(getClass().getResource(viewName));
			view.setLayoutX(1180);
			contextView.getChildren().clear();
			contextView.getChildren().add(view);
			
			// set to context view
			TranslateTransition trans = new TranslateTransition(Duration.millis(800), view);
			trans.setByX(1180);
			trans.setFromX(1180);
			trans.setToX(0);
			
			trans.setAutoReverse(false);
			trans.setCycleCount(1);
			
			ParallelTransition move = new ParallelTransition(titleTrans, trans);
			move.play();
			
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
