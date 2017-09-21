package com.jdc.ishop.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PosMessageView {
	
	private static VBox VIEW;
	private static PosMessageView CONTROLLER;
	
	@FXML
	private VBox box;
	
	public static VBox getView(String ... messages) {
		try {
			
			if(null == VIEW) {
				FXMLLoader loader = new FXMLLoader(PosMessageView.class.getResource("PosMessageView.fxml"));
				VIEW = loader.load();
				CONTROLLER = loader.getController();
			}
			
			CONTROLLER.box.getChildren().clear();
			
			for(String str : messages) {
				Label lab = new Label(str);
				lab.getStyleClass().add("fs24");
				lab.getStyleClass().add("text4");
				
				CONTROLLER.box.getChildren().add(lab);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return VIEW;
	}



}
