package com.jdc.student.model.vo;

import com.jdc.student.model.entity.Class;
import com.jdc.student.model.entity.Course;

public class ClassVO {

	private Class classData;
	private Course course;
	
	public Class getClassData() {
		return classData;
	}
	public void setClassData(Class classData) {
		this.classData = classData;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getCourseName() {
		return course.getName();
	}
	
	public String getFees() {
		return course.getFeesStr();
	}
	
	public String getStartDate() {
		return classData.getStartDate().toString();
	}
	
	public String getDays() {
		return classData.getDays();
	}
	
	public String getDurationStr() {
		return String.format("%f Months", classData.getDuration());
	}
	
	public String getTime() {
		return String.format("%s - %s", classData.getStartTime(), classData.getEndTime());
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", getCourseName(), getStartDate());
	}
}
