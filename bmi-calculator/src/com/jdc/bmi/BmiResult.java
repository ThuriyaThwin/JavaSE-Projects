package com.jdc.bmi;

public class BmiResult {

	private Double resultValue;
	private String resultString;

	public BmiResult(Double resultValue, String resultString) {
		super();
		this.resultValue = resultValue;
		this.resultString = resultString;
	}

	public Double getResultValue() {
		return resultValue;
	}

	public void setResultValue(Double resultValue) {
		this.resultValue = resultValue;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

}
