package com.jdc.inputs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputController extends Application implements Initializable {
	
	@FXML
	private TextField input1;
	@FXML
	private Slider input2;
	@FXML
	private RadioButton input3;
	@FXML
	private PasswordField input4;
	@FXML
	private DatePicker input5;
	@FXML
	private TextArea input6;
	
	@FXML
	private Label ouput1;
	@FXML
	private Label ouput2;
	@FXML
	private Label ouput3;
	@FXML
	private Label ouput4;
	@FXML
	private Label ouput5;
	@FXML
	private Label ouput6;
	
	
	public void clear() {
		input1.clear();
		input2.setValue(0);
		input3.setSelected(true);
		input4.clear();
		input5.setValue(null);
		input6.clear();
		
		ouput1.setText("");
		ouput2.setText("");
		ouput3.setText("");
		ouput4.setText("");
		ouput5.setText("");
		ouput6.setText("");
		
	}
	
	public void show() {
		ouput1.setText(input1.getText());
		ouput2.setText(String.valueOf(input2.getValue()));
		ouput3.setText(input3.isSelected() ? "Male" : "Female");
		ouput4.setText(input4.getText());
		ouput5.setText(input5.getValue() == null ? "" : input5.getValue().toString());
		ouput6.setText(input6.getText());
	}
	

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("InputView.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clear();
	}

}
