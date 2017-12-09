package com.jdc.bmi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BmiCalculator implements Initializable{

    @FXML
    private TextField name;

    @FXML
    private TextField lbs;

    @FXML
    private TextField kg;

    @FXML
    private TextField feet;

    @FXML
    private TextField inches;

    @FXML
    private TextField meter;

    @FXML
    private Label result;

    @FXML
    private Label resultData;

    public void showResult() {
    	
    		double mass = getDoubleValue(kg.getText());
    		double height = getDoubleValue(meter.getText());
    		
    		BmiResult bmi = Calculator.Instance.getResult(mass, height);
    		result.setText(bmi.getResultString());
    		resultData.setText(bmi.getResultValue().toString());

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbs.textProperty().addListener((a,b,c) -> {
				// clear result
				clearResult();

				setKilogram();
			});
		
		ChangeListener<String> meterConverter = (a,b,c) -> {
				
				try {
					
					// clear result
					clearResult();
					
					// get feet value
					Double feetDouble = getDoubleValue(feet.getText());
					
					// get inches value
					Double inchesDouble = getDoubleValue(inches.getText());
					
					// change to inches
					Double totalInches = (feetDouble * 12) + inchesDouble;
					
					// convert to meter
					Double meterDouble = totalInches * 0.0254;
					
					meter.setText(meterDouble.toString());					
				} catch (NumberFormatException e) {
					feet.clear();
					inches.clear();
					
					result.setText("Please enter digit only");
				}

				
			};
		
		feet.textProperty().addListener(meterConverter);
		inches.textProperty().addListener(meterConverter);
		
	}

	private Double getDoubleValue(String text) {
		
		if(null != text && !text.isEmpty())
			return Double.valueOf(text);
		
		return 0d;
	}
	
	private void clearResult() {
		result.setText("");
		resultData.setText("");
	}

	private void setKilogram() {
		
		try {
			// get lbs value
			// convert to double
			Double lbsDouble = getDoubleValue(lbs.getText());
			
			// convert to kilogram
			Double kgDouble = lbsDouble * 0.453592;
			
			// set kg
			kg.setText(kgDouble.toString());			
		} catch (NumberFormatException e) {
			lbs.clear();
			result.setText("Please enter lbs value with digit.");
		}

	}

}
