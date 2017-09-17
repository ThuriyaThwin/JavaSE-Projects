package com.jdc.student.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.student.model.RegistrationModel;
import com.jdc.student.model.entity.Registration;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class RegistrationList implements Initializable {

    @FXML
    private TableView<Registration> table;
    
    private RegistrationModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new RegistrationModel();
		List<Registration> list = model.findAll();
		table.getItems().addAll(list);
	}

}
