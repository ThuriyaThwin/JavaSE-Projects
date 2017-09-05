package com.jdc.calc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Calculator implements Initializable{
	
	@FXML
	private Label result;
	@FXML
	private Label temp;
	@FXML
	private GridPane grid;
	
	private void pressButton(ActionEvent event) {
		Button btn = (Button) event.getSource();
		String text = btn.getText();
		
		switch(text) {
		case "C":
			clear();
			break;
		case "+/-":
			doNegative();
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
		case "*":
		case "/":
			pressOperator(text);
			break;
		default:
			pressNumber(text);
			break;
		}
	}

	private void pressNumber(String num) {
		
		String resultStr = result.getText();
		if(resultStr.equals("0")) {
			result.setText(num);
		} else {
			result.setText(resultStr.concat(num));
		}
		
	}

	private void doDecimal() {
		String resultStr = result.getText();
		if(!resultStr.contains(".")) {
			result.setText(resultStr.concat("."));
		}
	}

	private void doNegative() {
		String resultStr = result.getText();
		
		if(!resultStr.equals("0")) {
			if(resultStr.startsWith("-")) {
				result.setText(resultStr.replace("-", ""));
			} else {
				result.setText("-".concat(resultStr));
			}
		}
		
	}

	private void clear() {
		result.setText("0");
		temp.setText("");
	}

	private void pressOperator(String operator) {
		String resultStr = result.getText();
		String tempStr = temp.getText();

		clear();
		
		if(tempStr.isEmpty()) {
			temp.setText(String.format("%s %s", resultStr, operator));
		} else {
			String [] array = tempStr.split(" ");
			String tempResult = calculate(array[0], array[1], resultStr);
			temp.setText(String.format("%s %s", tempResult, operator));
		}
	}

	private void calculate() {
		String tempStr = temp.getText();
		if(!tempStr.isEmpty()) {
			String resultStr = result.getText();

			String [] array = tempStr.split(" ");
			String tempResult = calculate(array[0], array[1], resultStr);
			clear();
			result.setText(tempResult);
		}
	}

	private void doPercent() {
		calculate();
		String tempStr = result.getText();
		
		Double d = Double.parseDouble(tempStr) / 100;
		result.setText(d.toString());
	}

	private String calculate(String strDigit1, String operator, String strDigit2) {
		
		Double d1 = Double.valueOf(strDigit1);
		Double d2 = Double.valueOf(strDigit2);
		
		Double d3 = 0d;
		
		switch(operator) {
		case "+":
			d3 = d1 + d2;
			break;
		case "-":
			d3 = d1 - d2;
			break;
		case "*":
			d3 = d1 * d2;
			break;
		case "/":
			d3 = d1 / d2;
			break;
		}
		
		return d3.toString();
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		clear();
		
		grid.getChildren().stream()
			.filter(node -> node instanceof Button)
			.map(node -> (Button)node)
			.forEach(btn -> btn.setOnAction(this::pressButton));
	}
	
}
