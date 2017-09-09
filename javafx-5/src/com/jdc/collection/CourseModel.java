package com.jdc.collection;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CourseModel {
	
	private static CourseModel model;
	private List<Course> courses;
	
	public static CourseModel getModel() {
		
		if(null == model) {
			model = new CourseModel();
		}
		
		return model;
	}
	
	public List<Course> getCourses() {
		return courses;
	}
	
	private CourseModel() {
		try {
			courses = Files.lines(Paths.get("course.txt"))
				.map(Course::new)
				.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
