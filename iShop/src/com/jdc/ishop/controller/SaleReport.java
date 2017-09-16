package com.jdc.ishop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SaleReport {

    @FXML
    private ComboBox<?> schCategory;

    @FXML
    private DatePicker schDateFrom;

    @FXML
    private DatePicker schDateTo;

    @FXML
    private TextField schEmployee;

    @FXML
    void clear(ActionEvent event) {

    }

    @FXML
    void table(ActionEvent event) {

    }

}
