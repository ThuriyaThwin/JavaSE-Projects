package com.jdc.ishop.controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.jdc.ishop.model.entity.Invoice;
import com.jdc.ishop.model.entity.Item;
import com.jdc.ishop.model.entity.SaleOrder;
import com.jdc.ishop.model.service.InvoiceService;
import com.jdc.ishop.model.service.ItemService;
import com.jdc.ishop.utils.ItemView;
import com.jdc.ishop.utils.Security;
import com.jdc.ishop.utils.ShoppingCart;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class SalePos implements Initializable, Consumer<Item>{

    @FXML
    private TextField barcode;

    @FXML
    private TextField item;

    @FXML
    private TextField category;

    @FXML
    private TilePane itemBox;

    @FXML
    private StackPane cargo;

    @FXML
    private Label subTotal;
    @FXML
    private Label tax;

    @FXML
    private Label total;
    
    private List<ShoppingCart> carts;
    private int index;

    @FXML
    void createCart(MouseEvent event) {
    	carts.add(new ShoppingCart(subTotal, tax, total));
    	subTotal.setText("0");
    	tax.setText("0");
    	total.setText("0");
    	index = carts.size() - 1;
    	
		cargo.getChildren().clear();
		cargo.getChildren().add(carts.get(index));
    }

    @FXML
    void deleteCart(MouseEvent event) {
    	carts.remove(index);
    	index = carts.size() - 1;
    	
    	if(index <0) {
    		createCart(null);
    	} else {
    		cargo.getChildren().clear();
    		cargo.getChildren().add(carts.get(index));
    	}
    }

    @FXML
    void paid(MouseEvent event) {
    	List<SaleOrder> orders = carts.get(index).getOrders();
    	Invoice invoice = new Invoice();
    	invoice.setCount(orders.stream().mapToInt(od -> od.getCount()).sum());
    	invoice.setSubTotal(Integer.parseInt(subTotal.getText().replaceAll(",", "")));
    	invoice.setTax(Integer.parseInt(tax.getText().replaceAll(",", "")));
    	invoice.setTotal(Integer.parseInt(total.getText().replaceAll(",", "")));
    	invoice.setSaleEmployee(Security.getLoginUser().getLogin());
    	invoice.setModifiedUser(Security.getLoginUser().getLogin());
    	invoice.setInvoiceDate(LocalDateTime.now());
    	
    	invoiceService.create(invoice, orders);
    	
    	// TODO show total amount to view
    }
    
    private ItemService itemService;
    private InvoiceService invoiceService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		carts = new ArrayList<>();
		carts.add(new ShoppingCart(subTotal, tax, total));
		
		cargo.getChildren().add(carts.get(index));
		
		itemService = ItemService.getInstance();
		invoiceService = InvoiceService.getInstance();
		
		barcode.textProperty().addListener((a,b,c) -> search());
		item.textProperty().addListener((a,b,c) -> search());
		category.textProperty().addListener((a,b,c) -> search());
		
		search();
	}

	@Override
	public void accept(Item t) {
		carts.get(index).addItem(t);
	}

	private void search() {
		List<Item> items = itemService.find(barcode.getText(), item.getText(), category.getText());
		
		itemBox.getChildren().clear();
		itemBox.getChildren().addAll(items.stream().map(a -> new ItemView(a, SalePos.this)).collect(Collectors.toList()));
	
	}
	

}
