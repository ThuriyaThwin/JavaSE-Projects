package com.jdc.ishop.controller;

import java.util.function.Consumer;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Member;
import com.jdc.ishop.model.entity.Member.Role;
import com.jdc.ishop.utils.MessageHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberEdit {

    @FXML
    private TextField name;

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;

    @FXML
    private CheckBox isAdmin;

    @FXML
    private CheckBox isDelete;

    @FXML
    private Label title;

    private Member item;
    private Consumer<Member> saveHandler;
    
    @FXML
    void close(ActionEvent event) {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save(ActionEvent event) {
    	
    	try {
        	// get view Data
        	if(null == item) {
        		item = new Member();
        		item.setLogin(loginId.getText());
        	}
        	
        	// get view data
        	item.setName(name.getText());
        	item.setPhone(phone.getText());
        	item.setPassword(password.getText());
        	item.setEmail(email.getText());
        	item.setRole(isAdmin.isSelected() ? Role.Manager : Role.Sale);
        	
        	// save
        	saveHandler.accept(item);
        	
        	// close
        	title.getScene().getWindow().hide();
        	
		} catch (IShopException e) {
			MessageHandler.getManagerHandler().alert(e);
		}
    	
    }

	public static void showView(Member item, Consumer<Member> saveHandler) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(MemberEdit.class.getResource("MemberEdit.fxml"));
			Parent view = loader.load();
			MemberEdit edit = loader.getController();
			edit.title.setText(null == item ? "Add New Member" : "Edit Member");
			edit.setItem(item);
			edit.saveHandler = saveHandler;
			
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(new Scene(view));
			stage.show();
			
		} catch (Exception e) {
			throw new IShopException("Please contact to Dev Team.");
		}
		
	}

	private void setItem(Member item) {
		this.item = item;
		if(null != item) {
			loginId.setEditable(false);
			loginId.setText(item.getLogin());
			name.setText(item.getName());
			password.setText(item.getPassword());
			phone.setText(item.getPhone());
			email.setText(item.getEmail());
			isAdmin.setSelected(item.getRole() == Role.Manager);
			isDelete.setSelected(item.isDelete());
		}
	}
}
