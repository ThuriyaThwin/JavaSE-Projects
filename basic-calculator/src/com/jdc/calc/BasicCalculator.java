package com.jdc.calc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BasicCalculator implements Initializable{

    @FXML
    private Label temp;

    @FXML
    private Label result;

    @FXML
    private GridPane grid;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for(Node n : grid.getChildren()) {
			if(n instanceof Button) {
				Button b = (Button) n;
				b.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						pressButton(b.getText());
					}
				});
			}
		}
		
		clear();
	}
	
	private void pressButton(String str) {
		switch(str) {
		case "C":
			clear();
			break;
		case "+/-":
			plusOrMinus();
			break;
		case "%":
			doPercent();
			break;
		case ".":
			doDecimal();
			break;
		case "=":
			calculate();
			break;
		case "+":
		case "-":
		case "⨉":
		case "÷":
			pressOperator(str);
			break;
		default:
			pressNumber(str);
			break;
		}
	}

	private void pressNumber(String str) {
		String string = result.getText();
		if(string.equals("0")) {
			result.setText(str);
		} else {
			result.setText(string.concat(str));
		}
	}

	private void pressOperator(String str) {
		String tempStr = temp.getText();
		String resultStr = result.getText();
		clear();
		if(tempStr.isEmpty()) {
			temp.setText(String.format("%s %s", resultStr, str));
		} else {
			String [] array = tempStr.split(" ");
			Double resultValue = calculate(array[0], resultStr, array[1]);
			temp.setText(String.format("%s %s", resultValue.toString(), str));
		}
		
	}

	private Double calculate(String str1, String str2, String operator) {
		Double d1 = Double.valueOf(str1);
		Double d2 = Double.valueOf(str2);
		Double d3 = 0d;
		
		switch (operator) {
		case "+":
			d3 = d1 + d2;
			break;
		case "-":
			d3 = d1 - d2;
			break;
		case "⨉":
			d3 = d1 * d2;
			break;
		case "÷":
			d3 = d1 / d2;
			break;
		default:
			break;
		}
		return d3;
	}

	private void calculate() {
		if(!temp.getText().isEmpty()) {
			String tempStr = temp.getText();
			String resultStr = result.getText();
			clear();
			String [] array = tempStr.split(" ");
			Double resultValue = calculate(array[0], resultStr, array[1]);
			result.setText(resultValue.toString());
		}
	}

	private void doDecimal() {
		String string = result.getText();
		
		if(!string.contains(".")) {
			result.setText(string.concat("."));
		}
		
	}

	private void doPercent() {
		calculate();
		String resultStr = result.getText();
		Double resultValue = Double.parseDouble(resultStr) / 100;
		result.setText(resultValue.toString());
 	}

	private void plusOrMinus() {
		String string = result.getText();
		if(!string.equals("0")) {
			if(string.startsWith("-")) {
				result.setText(string.replace("-", ""));
			} else {
				result.setText("-".concat(string));
			}
		}
	}

	private void clear() {
		temp.setText("");
		result.setText("0");
	}

}
