package com.jdc.multi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainView implements Initializable {

	@FXML
	private MenuBar menuBar;

	@FXML
	private HBox iconBar;

	@FXML
	private StackPane contentView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		menuBar.getMenus().stream().flatMap(menu -> menu.getItems().stream()).forEach(item -> {
			item.setOnAction(event -> {
				String name = item.getText();

				if (name.equals("Close")) {
					contentView.getScene().getWindow().hide();
				} else {
					loadView(name);
				}
			});
		});

		iconBar.getChildren().stream().map(node -> (VBox) node).forEach(node -> {

			node.setOnMouseClicked(event -> {
				String id = node.getId();
				if (id.equals("Close")) {
					contentView.getScene().getWindow().hide();
				} else {
					loadView(id);
				}
			});

			node.setOnMouseEntered(event -> {
				node.getStyleClass().remove("Back1");
				node.getStyleClass().add("Back2");

				node.getChildren().forEach(icon -> icon.getStyleClass().remove("Text1"));
				node.getChildren().forEach(icon -> icon.getStyleClass().add("Text3"));
			});

			node.setOnMouseExited(event -> {
				node.getStyleClass().remove("Back2");
				node.getStyleClass().add("Back1");

				node.getChildren().forEach(icon -> icon.getStyleClass().remove("Text3"));
				node.getChildren().forEach(icon -> icon.getStyleClass().add("Text1"));
			});

		});

	}

	private void loadView(String viewName) {

		try {
			
			double topY = (0-contentView.getPrefHeight()) * 1.5;

			String fxml = String.format("%s.fxml", viewName.replaceAll(" ", ""));
			Parent view = FXMLLoader.load(getClass().getResource(fxml));
			TranslateTransition upTrans = null;
			
			if(contentView.getChildren().size() > 0) {
				Node oldView = contentView.getChildren().get(0);
				
				upTrans = new TranslateTransition(Duration.millis(300), oldView);
				upTrans.setFromY(0);
				upTrans.setToY(topY);
				upTrans.setCycleCount(1);
				upTrans.setAutoReverse(false);
			}
			
			TranslateTransition downTrans = new TranslateTransition(Duration.millis(300), view);
			downTrans.setFromY(topY);
			downTrans.setToY(0);
			downTrans.setCycleCount(1);
			downTrans.setAutoReverse(false);

			SequentialTransition trans = new SequentialTransition();
			
			if(null != upTrans) {
				upTrans.setOnFinished(event -> {
					contentView.getChildren().clear();
					view.setLayoutY(topY);
					contentView.getChildren().add(view);
				});
				trans.getChildren().add(upTrans);
			} else {
				view.setLayoutY(topY);
				contentView.getChildren().add(view);
			}
			
			trans.getChildren().add(downTrans);
			
			trans.setAutoReverse(false);
			trans.setCycleCount(1);
			
			trans.play();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
