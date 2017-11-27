package com.jdc.bmi;

public enum Calculator {
	
	Instance;
	
	public BmiResult getResult(double mass, double height) {
		double result = mass/(height * height);
		
		String str = null;
		if(result < 16) {
			str = "Severe Thinness";
		} else if (result >= 16 && result < 17) {
			str = "Moderate Thinness";
		} else if (result >= 17 && result < 18.5) {
			str =  "Mild Thinness";
		} else if (result >= 18.5 && result < 25) {
			str = "Normal";
		} else if (result >= 25 && result < 30) {
			str = "Overweight";
		} else if (result >= 30 && result < 35) {
			str = "Obese Class I";
		} else if (result >= 35 && result < 40) {
			str = "Obese Class II";
		} else {
			str = "Obese Class III";
		}
		
		return new BmiResult(result, str);
	}
}
