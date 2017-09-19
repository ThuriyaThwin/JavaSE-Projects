package com.jdc.ishop.utils;

import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Item;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class ItemView extends VBox {
	
	
	public ItemView(Item item, Consumer<Item> addHandler) {
		super();
		
		getStyleClass().add("item");
		getStyleClass().add("back1");
		
		// category
		Label cat = new Label(item.getCategoryName());
		cat.getStyleClass().add("category");
		getChildren().add(cat);
		
		// name & price
		Label name = new Label(item.getName());
		Label price = new Label(String.valueOf(item.getPrice()));
		
		VBox npBox = new VBox();
		npBox.getChildren().add(name);
		npBox.getChildren().add(price);
		
		
		// add icon
		HBox buttonBox = new HBox();
		SVGPath plus = new SVGPath();
		plus.setContent("M31 12h-11v-11c0-0.552-0.448-1-1-1h-6c-0.552 0-1 0.448-1 1v11h-11c-0.552 0-1 0.448-1 1v6c0 0.552 0.448 1 1 1h11v11c0 0.552 0.448 1 1 1h6c0.552 0 1-0.448 1-1v-11h11c0.552 0 1-0.448 1-1v-6c0-0.552-0.448-1-1-1z");
		buttonBox.getChildren().add(plus);
		buttonBox.setOnMouseClicked(e -> addHandler.accept(item));
		
		buttonBox.setOnMouseClicked(e -> addHandler.accept(item));
		
		HBox btnAndDisp = new HBox();
		btnAndDisp.getChildren().addAll(npBox, buttonBox);
		HBox.setHgrow(npBox, Priority.ALWAYS);
		
		getChildren().add(btnAndDisp);
	}

}
