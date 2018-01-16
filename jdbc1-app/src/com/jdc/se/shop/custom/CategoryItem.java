package com.jdc.se.shop.custom;

import com.jdc.online.entity.Category;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

public class CategoryItem extends HBox{
	
	private Category category;
	private SVGPath icon;
	private Label label;
	private TextField textField;
	
	public CategoryItem(Category category) {
		icon = new SVGPath();
		label = new Label();
		textField = new TextField();
		label.textProperty().bind(textField.textProperty());
		textField.setText(category.getName());
		this.category = category;
		
		getChildren().addAll(icon, label);
	}
	
	public Category getCategory() {
		category.setName(label.getText());
		return category;
	}

}
