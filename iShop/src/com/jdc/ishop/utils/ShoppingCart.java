package com.jdc.ishop.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.entity.SaleOrder;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ShoppingCart extends VBox  {
	
	private DoubleProperty subTotalProperty;
	private DoubleProperty totalProperty;
	private DoubleProperty taxProperty;
	
	private Label subTotal;
	private Label tax;
	private Label total;
	private DecimalFormat df;
	
	public ShoppingCart(Label subTotal, Label tax, Label total) {
		df = new DecimalFormat("###,###,###,###");
		getStyleClass().add("shopping-cart");
		subTotalProperty = new SimpleDoubleProperty();
		taxProperty = new SimpleDoubleProperty();
		totalProperty = new SimpleDoubleProperty();
		
		taxProperty.bind(subTotalProperty.multiply(0.05));
		totalProperty.bind(subTotalProperty.add(taxProperty));
		
		this.subTotal = subTotal;
		this.tax = tax;
		this.total = total;
		
		subTotalProperty.addListener((a,b,c) -> this.subTotal.setText(df.format(subTotalProperty.get())));
		taxProperty.addListener((a,b,c) -> this.tax.setText(df.format(taxProperty.get())));
		totalProperty.addListener((a,b,c) -> this.total.setText(df.format(totalProperty.get())));
	}

	public void addItem(Item item) {
		
		SaleItem saleItem = find(item);
		
		if(null != saleItem) {
			saleItem.countUp();
		} else {
			SaleOrder order = new SaleOrder();
			order.setItemId(item.getId());
			order.setItemName(item.getName());
			order.setCategory(item.getCategoryName());
			order.setCount(1);
			order.setPrice(item.getPrice());
			order.setTotal(item.getPrice() * 1);
			
			saleItem = new SaleItem(order);
			getChildren().add(saleItem);
			
			calculate();
			saleItem.totalProperty().addListener((a,b,c) -> calculate());
		}
	}
	
	private void calculate() {
		subTotalProperty.set(0);
		getChildren().stream().filter(a -> a instanceof SaleItem)
			.map(a -> (SaleItem)a)
			.forEach(a -> {
				subTotalProperty.set(subTotalProperty.get() + a.totalProperty().doubleValue());
			});
		
	}

	private SaleItem find(Item item) {
		return getChildren().stream().filter(a -> a instanceof SaleItem)
				.map(a -> (SaleItem)a)
				.filter(a -> a.getOrder().getItemId() == item.getId())
				.findAny().orElse(null);
	}

	public List<SaleOrder> getOrders() {
		return getChildren().stream().filter(a -> a instanceof SaleItem)
				.map(a -> (SaleItem)a)
				.map(a -> a.getOrder())
				.collect(Collectors.toList());
	}
	
	

}
