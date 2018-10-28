package com.jdc.teacher.common;

import com.jdc.teacher.entity.ClassRoom;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class ClassRoomItem extends HBox {

	public ClassRoomItem(ClassRoom item) {
		super();

		SVGPath icon = new SVGPath();
		icon.setContent(
				"M-256,411v-2h-2v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-2v2h-2v2h34v-2H-256z M-272,381h2l16,10v2h-34v-2L-272,381zM-256,411v-2h-2v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-6v-12h2v-2h-6v2h2v12h-2v2h-2v2h34v-2H-256z M-272,381h2l16,10v2h-34v-2L-272,381z");
		VBox iconBox = new VBox();
		iconBox.getStyleClass().add("iconBox");
		iconBox.getChildren().add(icon);

		Label grade = new Label(item.getGrade().toString());
		Label year = new Label(item.getYear());
		year.getStyleClass().add("fs18");
		VBox dataBox = new VBox();
		dataBox.getChildren().addAll(year, grade);
		dataBox.getStyleClass().add("dataBox");

		getChildren().addAll(iconBox, dataBox);
		getStyleClass().add("classRoomItem");

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(3.0);
		dropShadow.setOffsetX(2.0);
		dropShadow.setOffsetY(2.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		
		setEffect(dropShadow);
	}

}
