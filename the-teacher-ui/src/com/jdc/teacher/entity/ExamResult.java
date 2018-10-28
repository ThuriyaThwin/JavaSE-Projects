package com.jdc.teacher.entity;

import com.jdc.teacher.entity.ClassRoom.Grade;

public class ExamResult {

	private int id;
	private Student student;
	private int burmese;
	private int english;
	private int maths;
	private Exam exam;

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getBurmese() {
		return burmese;
	}

	public void setBurmese(int burmese) {
		this.burmese = burmese;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public int getTotal() {
		return burmese + english + maths;
	}

	public int getAverage() {
		return getTotal() / 3;
	}

	public String getResult() {

		if (burmese < 40 || english < 40 || maths < 40) {
			return "Fail";
		}

		return "Pass";
	}

	public String getName() {
		return student == null ? null : student.getName();
	}

	public Grade getGrade() {
		return student == null ? null : student.getGrade();
	}

	public String getYear() {
		return student == null ? null : student.getYear();
	}

	public enum Exam {
		First, Second, Final;
	}
}
