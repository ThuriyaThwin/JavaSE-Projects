package com.jdc.student.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainFrame implements Initializable{
	
	private static MainFrame CONTROLLER;

	public static MainFrame getController() {
		return CONTROLLER;
	}
	
	@FXML
	private VBox menuWrapper;
	@FXML
	private Label title;
	@FXML
	private Label message;
	@FXML
	private StackPane contentView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuWrapper.getChildren().stream()
			.filter(node -> node instanceof Label)
			.map(node -> (Label)node)
			.forEach(label -> {
				label.setOnMouseClicked(event -> navigate(label.getText()));
			});
		
		navigate("Registration List");
		
		CONTROLLER = this;
	}
	
	public void navigate(String viewName) {
	
		try {
			message.setText("");
			title.setText(viewName);
			
			// load view
			Parent view = FXMLLoader.load(getClass()
					.getResource(viewName.replaceAll(" ", "")
							.concat(".fxml")));
			
			// clear contents
			contentView.getChildren().clear();
			
			// add view to content
			contentView.getChildren().add(view);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMessage(String m) {
		message.setText(m);
	}
}
