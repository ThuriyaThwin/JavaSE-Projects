package com.jdc.hotel.application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChangePassword {
	
    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    public static void showView() {
		try {
			
			Stage stage = new Stage(StageStyle.UNDECORATED);
			Parent root = FXMLLoader.load(ChangePassword.class.getResource("ChangePassword.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void close() {
    	
    }
    
    public void save() {
    	
    }

}
