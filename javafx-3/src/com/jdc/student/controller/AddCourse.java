package com.jdc.student.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.student.StudentException;
import com.jdc.student.model.CourseModel;
import com.jdc.student.model.entity.Course;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddCourse implements Initializable{

	@FXML
	private TextField name;
	@FXML
	private TextField fees;
	@FXML
	private Slider duration;
	@FXML
	private TextArea description;
	
	private CourseModel model;
	
	public void clear() {
		name.clear();
		fees.clear();
		duration.setValue(0);
		description.clear();
	}
	
	public void save() {
		
		try {
			// check user inputs
			checkInputs();
			
			// get user inputs 
			Course c = new Course();
			c.setName(name.getText());
			c.setFees(Integer.parseInt(fees.getText()));
			c.setDuration(duration.getValue());
			c.setDescription(description.getText());
			
			// save to database
			model.create(c);
			
			// navigate to course list view
			MainFrame.getController().navigate("Course List");
			
		} catch (StudentException e) {
			e.printStackTrace();
		}
	}

	private void checkInputs() {
		// name
		if(name.getText().isEmpty()) {
			throw new StudentException("Please enter course name.");
		}
		// fees
		if(fees.getText().isEmpty()) {
			throw new StudentException("Please enter course fees.");
		}
		
		try {
			Integer.parseInt(fees.getText());
		} catch (Exception e) {
			throw new StudentException("Please enter fees with digit only.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new CourseModel();
	}
}
