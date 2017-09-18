package com.jdc.ishop.utils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageView {

    @FXML
    private Label message;

    @FXML
    void close(MouseEvent event) {
    	message.getScene().getWindow().hide();
    }
    
    public static void showMessage(String message) {
    	try {
			
    		FXMLLoader loader = new FXMLLoader(MessageView.class.getResource("MessageView.fxml"));
    		Parent root = loader.load();
    		
    		MessageView controller = loader.getController();
    		controller.message.setText(message);
    		
    		Stage stage = new Stage(StageStyle.UNDECORATED);
    		stage.setScene(new Scene(root));
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.show();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
