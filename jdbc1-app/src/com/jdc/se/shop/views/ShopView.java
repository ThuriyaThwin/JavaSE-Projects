package com.jdc.se.shop.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.se.shop.NavigationHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ShopView implements Initializable{

    @FXML
    private MenuBar menubar;

    @FXML
    private HBox toolBar;

    @FXML
    private Label title;

    @FXML
    private StackPane stack;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    		
    		// initialize navigation handler
    		NavigationHandler.init(stack, title);
    		
    		// load menu
    		menubar.getMenus().stream().flatMap(menu -> menu.getItems().stream())
    			.filter(item -> item instanceof MenuItem)
    			.forEach(item -> item.setOnAction(event -> NavigationHandler.getHandler().navigate(item.getText().toLowerCase().replaceAll(" ", ""))));
    		
    		// load tools bar
    		toolBar.getChildren().stream().filter(node -> node instanceof VBox)
    			.map(node -> (VBox)node)
    			.forEach(box -> box.setOnMouseClicked(event -> {
    				box.getChildren().stream().filter(node -> node instanceof Label)
    					.map(node -> (Label)node)
    					.findAny().ifPresent(label -> {
    						String key = label.getText().toLowerCase().replaceAll(" ", "");
    						NavigationHandler.getHandler().navigate(key);
    					});
    			}));
    		
    		// load home 
    		NavigationHandler.getHandler().navigate("home");
	}

}
