package com.jdc.se.shop.custom;

import com.jdc.online.entity.Township;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TownshipItem extends HBox{

	public TownshipItem(Township township) {
		super();
		Label label = new Label(township.getName());
		getStyleClass().add("township");
		getStyleClass().add("WorningBack");
		label.getStyleClass().add("WorningText");
		getChildren().add(label);
	}
}
