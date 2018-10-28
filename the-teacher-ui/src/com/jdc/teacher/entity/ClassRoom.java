package com.jdc.teacher.entity;

public class ClassRoom {

	private String year;
	private Grade grade;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public enum Grade {
		One, Two, Three, Four, Five;

		@Override
		public String toString() {
			return String.format("Grade %s", name());
		}
	}
}
