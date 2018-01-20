package com.jdc.se.shop.views;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.se.shop.service.OrderService;
import com.jdc.se.shop.service.vo.OrderVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderManagement implements Initializable {

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private TextField customerName;

	@FXML
	private TextField orderId;

	@FXML
	private TableView<OrderVO> table;
	
	private OrderService service;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = OrderService.getService();
		
		dateFrom.valueProperty().addListener((a,b,c) -> loadData());
		dateTo.valueProperty().addListener((a,b,c) -> loadData());
		customerName.textProperty().addListener((a,b,c) -> loadData());
		orderId.textProperty().addListener((a,b,c) -> loadData());
		
		dateFrom.setValue(LocalDate.now().minusDays(3));
	}

	private void loadData() {
		table.getItems().clear();
		
		List<OrderVO> list = service.search(dateFrom.getValue(), dateTo.getValue(), customerName.getText(), orderId.getText());
		
		table.getItems().addAll(list);
	}

}
