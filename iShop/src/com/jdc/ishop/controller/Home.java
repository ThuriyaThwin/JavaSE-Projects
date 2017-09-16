package com.jdc.ishop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Home {

    @FXML
    private BarChart<?, ?> chart;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Label members;

    @FXML
    private Label categories;

    @FXML
    private Label items;

    @FXML
    void reset(ActionEvent event) {

    }

}
