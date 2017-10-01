package com.jdc.flag.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GameFrame implements Initializable{

    @FXML
    private Label player;

    @FXML
    private Label total;

    @FXML
    private Label right;

    @FXML
    private Label wrong;

    @FXML
    private StackPane contentView;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {

    }
    
    @FXML
    void setting(ActionEvent event) {

    }
    

	public static HBox getView() {
		try {
			HBox box = FXMLLoader.load(Login.class.getResource("GameFrame.fxml"));
			return box;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
