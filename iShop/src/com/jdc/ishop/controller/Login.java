package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Member;
import com.jdc.ishop.model.entity.Member.Role;
import com.jdc.ishop.model.service.MemberService;
import com.jdc.ishop.utils.Security;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login implements Initializable{

    @FXML
    private TextField loginID;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    
    private MemberService service;

    @FXML
    void close(ActionEvent event) {
    		Platform.exit();
    }

    @FXML
    void login(ActionEvent event) {
    	try {
        	Member member = service.login(loginID.getText(), password.getText());
        	Security.setLoginUser(member);
        	
        	if(member.getRole() == Role.Manager) {
        		// show manager home
        		ManagerHome.showView();
        	} else {
        		// show pos
        		SaleHome.showView();
        	}
        	
    		loginID.getScene().getWindow().hide();
        	
		} catch (IShopException e) {
			message.setText(e.getMessage());
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = MemberService.getInstance();
	}

}
