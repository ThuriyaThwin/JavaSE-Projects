package com.jdc.se.shop.custom;

import java.util.function.Consumer;

import com.jdc.online.entity.Product;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class ProductItem extends VBox{

	public ProductItem(Product product, Consumer<Product> consumer) {
		
		Label lblName = new Label();
		Label lblPrice = new Label();
		TextField name = new TextField();
		TextField price = new TextField();
		
		lblName.textProperty().bind(name.textProperty());
		lblPrice.textProperty().bind(price.textProperty());
		
		name.setText(product.getName());
		price.setText(String.valueOf(product.getPrice()));

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
	
		getStyleClass().add("product");
		getStyleClass().add("WorningBack");
		lblName.getStyleClass().add("WorningText");
		lblPrice.getStyleClass().add("WorningText");
		getChildren().addAll(lblName, lblPrice, edit);
		
		editBox.setOnMouseClicked(event -> {
			getChildren().clear();
			getChildren().addAll(name, price, save);
		});
		
		saveBox.setOnMouseClicked(event -> {
			product.setName(name.getText());
			product.setPrice(Integer.parseInt(price.getText()));
			consumer.accept(product);
		});
		
	}
	
}
