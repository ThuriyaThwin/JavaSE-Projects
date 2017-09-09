package com.jdc.collection;

public class Course {

	private String name;
	private double duration;
	private String description;
	private int fees;

	public Course(String line) {
		String [] array = line.split("\t");
		name = array[0];
		duration = Double.parseDouble(array[1]);
		description = array[2];
		fees = Integer.parseInt(array[3]);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
