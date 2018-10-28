package com.jdc.se.shop.custom;

import java.util.function.Consumer;

import com.jdc.online.entity.Category;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.SVGPath;

public class CategoryItem extends HBox{
	
	private Category category;
	private Label label;
	private TextField textField;

	
	public CategoryItem(Category category, Consumer<Category> consumer) {
		
		SVGPath edit = new SVGPath();
		SVGPath save = new SVGPath();
		edit.setScaleX(0.5);
		edit.setScaleY(0.5);
		
		HBox editBox = new HBox();
		editBox.getChildren().add(edit);
		
		HBox saveBox = new HBox();
		saveBox.getChildren().add(save);
		
		edit.setContent("M27 0c2.761 0 5 2.239 5 5 0 1.126-0.372 2.164-1 3l-2 2-7-7 2-2c0.836-0.628 1.874-1 3-1zM2 23l-2 9 9-2 18.5-18.5-7-7-18.5 18.5zM22.362 11.362l-14 14-1.724-1.724 14-14 1.724 1.724z");
		save.setContent("M28 0h-28v32h32v-28l-4-4zM16 4h4v8h-4v-8zM28 28h-24v-24h2v10h18v-10h2.343l1.657 1.657v22.343z");

		label = new Label();
		textField = new TextField();
		label.textProperty().bind(textField.textProperty());
		
		getStyleClass().add("category");
		getStyleClass().add("WorningBack");
		label.getStyleClass().add("WorningText");

		textField.setText(category.getName());
		this.category = category;
		
		setHgrow(label, Priority.ALWAYS);
		setSpacing(10);
		getChildren().addAll(editBox, label);
		
		editBox.setOnMouseClicked(event -> {
			getChildren().removeAll(editBox, label);
			getChildren().addAll(saveBox, textField);
		});
		
		saveBox.setOnMouseClicked(event -> {
			consumer.accept(getCategory());
		});
	}
	
	public Category getCategory() {
		category.setName(label.getText());
		return category;
	}

}
