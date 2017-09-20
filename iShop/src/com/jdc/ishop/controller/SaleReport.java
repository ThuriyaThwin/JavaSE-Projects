package com.jdc.ishop.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.SaleOrder;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.model.service.OrderService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleReport implements Initializable{

    @FXML
    private ComboBox<Category> schCategory;

    @FXML
    private DatePicker schDateFrom;

    @FXML
    private DatePicker schDateTo;

    @FXML
    private TextField schEmployee;
    
    @FXML
    private TableView<SaleOrder> table;

    @FXML
    void clear(ActionEvent event) {
    	schCategory.setValue(null);
    	schDateFrom.setValue(LocalDate.now().withDayOfMonth(1));
    	schDateTo.setValue(null);
    	schEmployee.clear();
    }
    
    private OrderService service;
    private CategoryService catService;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = OrderService.getInstance();
		catService = CategoryService.getInstance();
		
		schCategory.getItems().addAll(catService.find(false));
		
		schCategory.valueProperty().addListener((a,b,c) -> search());
		schDateFrom.valueProperty().addListener((a,b,c) -> search());
		schDateTo.valueProperty().addListener((a,b,c) -> search());
		schEmployee.textProperty().addListener((a,b,c) -> search());
		
		search();
	}


	private void search() {
		List<SaleOrder> list = service.find(schCategory.getValue(), schDateFrom.getValue(), schDateTo.getValue(), schEmployee.getText());
		table.getItems().clear();
		table.getItems().addAll(list);
	}

}
