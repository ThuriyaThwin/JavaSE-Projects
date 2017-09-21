package com.jdc.ishop.utils;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Invoice;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PosMessage {
	
	private static PosMessage INSTANCE;
	
	private StackPane contentView;
	
	private VBox container;
	
	public static PosMessage getInstance() {
		return INSTANCE;
	}
	
	private PosMessage(StackPane contentView) {
		this.contentView = contentView;
		container = new VBox();
		container.setAlignment(Pos.BOTTOM_CENTER);
	}
	
	public void showResult(Invoice invoice, PrintListener print) {
		VBox messageBox = PosMessageView.getView(
				"Invoice No. " + invoice.getId(), 
				"Sub Total : " + invoice.getSubTotal(),
				"Tax       : " + invoice.getTax(),
				"Total     : " + invoice.getTotal());
		messageBox.setOnMouseClicked(e -> {
			print.print(invoice);
			hide(messageBox);
		});
		show(messageBox);
	}

	public void showMessage(IShopException e) {
		VBox messageBox = PosMessageView.getView(e.getMessage());
		messageBox.setOnMouseClicked(event -> {
			hide(messageBox);
		});
		show(messageBox);
	}

	private void show(VBox messageBox) {
		container.getChildren().clear();
		container.getChildren().add(messageBox);
		
		messageBox.setLayoutY(780);
		
		contentView.getChildren().add(container);
		
		TranslateTransition trans = new TranslateTransition(Duration.millis(500), messageBox);
		trans.setFromY(780);
		trans.setToY(0);
		trans.setCycleCount(1);
		trans.setAutoReverse(false);
		
		trans.play();
	}

	private void hide(VBox messageBox) {
		TranslateTransition trans = new TranslateTransition(Duration.millis(500), messageBox);
		trans.setFromY(0);
		trans.setToY(780);
		trans.setCycleCount(1);
		trans.setAutoReverse(false);
		
		trans.setOnFinished(e -> contentView.getChildren().remove(container));
		
		trans.play();
	}



	public static void init(StackPane contentView) {
		INSTANCE = new PosMessage(contentView);
	}

}
