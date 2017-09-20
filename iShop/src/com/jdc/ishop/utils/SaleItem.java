package com.jdc.ishop.utils;

import java.text.DecimalFormat;

import com.jdc.ishop.model.entity.SaleOrder;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SaleItem extends VBox {
	
	private DecimalFormat df;
	
	private Label name;

	private Label unitPrice;
	private Label count;
	private Label total;
	
	private SaleOrder order;
	
	private DoubleProperty totalProperty;
	
	public SaleItem(SaleOrder order) {
		
		totalProperty = new SimpleDoubleProperty(0);
		
		this.order = order;
		df = new DecimalFormat("###,###,###");
		
		getStyleClass().add("order-item");
		
		name = new Label(order.getItemName());
		getChildren().add(name);
		
		VBox box1 = new VBox();
		box1.getChildren().add(new Label("Price"));
		unitPrice = new Label(df.format(order.getPrice()));
		box1.getChildren().add(unitPrice);
		
		VBox box2 = new VBox();
		box2.setAlignment(Pos.BOTTOM_RIGHT);
		box2.getChildren().add(new Label("Count"));
		box2.setPrefWidth(100);
		count = new Label("1");
		box2.getChildren().add(count);

		VBox box3 = new VBox();
		box3.setAlignment(Pos.BOTTOM_RIGHT);
		total = new Label(df.format(order.getPrice()));
		box3.getChildren().add(total);
		HBox.setHgrow(box3, Priority.ALWAYS);
		
		HBox box = new HBox();
		box.getStyleClass().add("Space10");
		box.getChildren().addAll(box1, box2, box3);
		getChildren().add(box);
		
		totalProperty.set(order.getTotal());
	}
	
	public void setCount(int count) {
		order.setCount(count);
		order.setTotal(count * order.getPrice());
		
		this.count.setText(String.valueOf(count));
		this.total.setText(df.format(order.getTotal()));
		totalProperty.set(order.getTotal());
	}
	
	public void countUp() {
		order.setCount(order.getCount() + 1);
		order.setTotal(order.getCount() * order.getPrice());
		
		this.count.setText(String.valueOf(order.getCount()));
		this.total.setText(df.format(order.getTotal()));
		totalProperty.set(order.getTotal());
	}
	
	
	public void countDown() {
		order.setCount(order.getCount() + 1);
		order.setTotal(order.getCount() * order.getPrice());
		
		this.count.setText(String.valueOf(order.getCount()));
		this.total.setText(df.format(order.getTotal()));
		totalProperty.set(order.getTotal());
	}
	
	public SaleOrder getOrder() {
		return order;
	}
	
	public DoubleProperty totalProperty() {
		return totalProperty;
	}

}
