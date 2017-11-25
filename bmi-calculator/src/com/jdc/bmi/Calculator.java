package com.jdc.bmi;

public enum Calculator {
	
	Instance;
	
	public String getResult(double mass, double height) {
		double result = mass/(height * height);
		
		if(result < 16) {
			return "Severe Thinness";
		} else if (result >= 16 && result < 17) {
			return "Moderate Thinness";
		} else if (result >= 17 && result < 18.5) {
			return "Mild Thinness";
		} else if (result >= 18.5 && result < 25) {
			return "Normal";
		} else if (result >= 25 && result < 30) {
			return "Overweight";
		} else if (result >= 30 && result < 35) {
			return "Obese Class I";
		} else if (result >= 35 && result < 40) {
			return "Obese Class II";
		} else {
			return "Obese Class III";
		}
	}
}
