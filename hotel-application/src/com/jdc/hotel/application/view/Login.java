package com.jdc.hotel.application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

    @FXML
    private Label message;

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField password;
    
    public void close() {
    		message.getScene().getWindow().hide();
    }
    
    public void managerLogin() {
    	
    }
    
    public void receptionLogin() {
    	
    }

}
