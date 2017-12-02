package com.jdc.hotel.application.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class AdminHome implements Initializable{

    @FXML
    private MenuBar menuBar;

    @FXML
    private HBox buttonBar;

    @FXML
    private Label title;

    @FXML
    private Label message;
    
    @FXML
    private StackPane content;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		message.setText("");
		title.setText("Hotel Manager Home");
		
		menuBar.getMenus().stream().flatMap(menu -> menu.getItems().stream())
			.filter(item -> !(item instanceof SeparatorMenuItem)).forEach(item -> {
				item.setOnAction(event -> {
					if(item.getText().equals("Close")) {
						title.getScene().getWindow().hide();
					} else if (item.getText().equals("Change Password")) {
						// show pop up
					} else {
						try {
							title.setText(item.getText());
							String view = item.getText().replaceAll(" ", "").concat(".fxml");
							Parent root = FXMLLoader.load(getClass().getResource(view));
							content.getChildren().clear();
							content.getChildren().add(root);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				});
			});
		
	}

}
