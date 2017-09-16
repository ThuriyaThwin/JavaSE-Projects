package com.jdc.ishop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private TextField loginID;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    @FXML
    void close(ActionEvent event) {
    		Platform.exit();
    }

    @FXML
    void login(ActionEvent event) {

    }

}
