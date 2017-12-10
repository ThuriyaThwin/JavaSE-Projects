package com.jdc.multi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

			String fxml = String.format("%s.fxml", viewName.replaceAll(" ", ""));
			Parent view = FXMLLoader.load(getClass().getResource(fxml));
			contentView.getChildren().clear();
			contentView.getChildren().add(view);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
