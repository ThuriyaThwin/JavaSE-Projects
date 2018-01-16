package com.jdc.se.shop;

import java.util.Properties;

import com.jdc.se.shop.views.ShopView;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class NavigationHandler {
	
	public static NavigationHandler handler;
	
	private StackPane contentView;
	private Label titleLabel;
	private Properties props;
	
	private NavigationHandler(StackPane contentView, Label titleLabel) {
		
		try {
			this.contentView = contentView;
			this.titleLabel = titleLabel;
			props = new Properties();
			props.load(this.getClass().getResourceAsStream("/META-INF/navigation.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init(StackPane contentView, Label titleLabel) {
		handler = new NavigationHandler(contentView, titleLabel);
	}
	
	public static NavigationHandler getHandler() {
		return handler;
	}
	
	
	public String getTitle(String key) {
		return props.getProperty("title.".concat(key));
	}
	
	public Node getView(String key) {
		try {
			String viewName = props.getProperty("view.".concat(key));
			return FXMLLoader.load(ShopView.class.getResource(viewName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void navigate(String key) {
		
		if("close".equals(key)) {
			Platform.exit();
		} else {
			navigate(getView(key), getTitle(key));
		}
	}
	
	public void navigate(Node view, String title) {
		contentView.getChildren().clear();
		contentView.getChildren().add(view);
		titleLabel.setText(title);
	}
}
