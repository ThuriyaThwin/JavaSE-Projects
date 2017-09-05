package com.jdc.hello;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloFX {
	
	@FXML
	private TextField input;
	@FXML
	private Label output;
	
	public void sayHello() {
		String value = input.getText();
		if(value.isEmpty()) {
			output.setText("Please enter your name!");
		} else {
			output.setText("Hello " + value);
		}
	}
}
