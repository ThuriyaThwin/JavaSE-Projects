package com.jdc.se.shop.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.online.entity.State;
import com.jdc.online.entity.Township;
import com.jdc.se.shop.service.CustomerService;
import com.jdc.se.shop.service.LocationService;
import com.jdc.se.shop.service.vo.CustomerVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerManagement implements Initializable{
	
    @FXML
    private ComboBox<State> states;

    @FXML
    private ComboBox<Township> townships;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TableView<CustomerVO> table;
    
    private CustomerService service;
    private LocationService locationService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = CustomerService.getService();
		locationService = LocationService.getService();
		
		name.textProperty().addListener((a,b,c) -> loadData());
		email.textProperty().addListener((a,b,c) -> loadData());
		townships.valueProperty().addListener((a,b,c) -> loadData());
		
		states.getItems().addAll(locationService.getAllState());
		states.itemsProperty().addListener((a,b,c) -> {
			townships.getItems().clear();
			townships.getItems().addAll(locationService.findTownshipByState(states.getValue()));
			if(!townships.getItems().isEmpty()) {
				townships.getSelectionModel().select(0);
			}
		});		

	}

	private void loadData() {
		table.getItems().clear();
		List<CustomerVO> list = service.find(townships.getValue(), name.getText(), email.getText());
		table.getItems().addAll(list);
	}

}
