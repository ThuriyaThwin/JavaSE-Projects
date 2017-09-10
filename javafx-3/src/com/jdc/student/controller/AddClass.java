package com.jdc.student.controller;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jdc.student.StudentException;
import com.jdc.student.model.ClassModel;
import com.jdc.student.model.CourseModel;
import com.jdc.student.model.entity.Class;
import com.jdc.student.model.entity.Course;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddClass implements Initializable{

	@FXML
	private ComboBox<Course> courseCombo;
	@FXML
	private DatePicker startDate;
	@FXML
	private HBox daysBox;
	@FXML
	private TextField startTime;
	@FXML
	private TextField endTime;
	
	@FXML
	private Slider duration;
	@FXML
	private TextArea remark;
	
	private ClassModel model;
	private CourseModel courseModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseModel = new CourseModel();
		model = new ClassModel();
		
		for(DayOfWeek d : DayOfWeek.values()) {
			CheckBox check = new CheckBox(d.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
			daysBox.getChildren().add(check);
		}
		
		courseCombo.getItems().addAll(courseModel.findAll());
	}

	public void clear() {
		courseCombo.setValue(null);
		startDate.setValue(null);
		daysBox.getChildren().stream().filter(a -> a instanceof CheckBox)
			.map(a -> (CheckBox)a).forEach(a -> a.setSelected(false));
		startTime.clear();
		endTime.clear();
		duration.setValue(0);
		remark.clear();
	}
	
	public void save() {
		
		try {
			// check inputs
			checkInputs();
			
			// get view data
			Class c = new Class();
			c.setCourse_id(courseCombo.getValue().getId());
			c.setStartDate(startDate.getValue());
			
			StringBuilder sb = new StringBuilder();
			daysBox.getChildren().stream().filter(a -> a instanceof CheckBox)
				.map(a -> (CheckBox)a)
				.filter(a -> a.isSelected())
				.forEach(a -> sb.append(a.getText()).append(", "));
			
			sb.substring(0, sb.length() - 2);
			c.setDays(sb.toString());
			
			c.setStartTime(startTime.getText());
			c.setEndTime(endTime.getText());
			c.setDuration(duration.getValue());
			c.setRemark(remark.getText());
			
			// add to database
			model.create(c);
			
			// navigate to class list
			MainFrame.getController().navigate("Class List");
			
		} catch (StudentException e) {
			e.printStackTrace();
		}
	}

	private void checkInputs() {
		
		// course
		if(null == courseCombo.getValue()) {
			throw new StudentException("Please select Course.");
		}
		
		// start date
		if(null == startDate.getValue()) {
			throw new StudentException("Please set a start date.");
		}
		
		// start time
		if(startTime.getText().isEmpty()) {
			throw new StudentException("Please enter Start Time.");
		}
		
		// end time
		if(endTime.getText().isEmpty()) {
			throw new StudentException("Please enter End Time.");
		}
		
		// duration
		if(duration.getValue() <= 0) {
			throw new StudentException("Please set duration for the class.");
		}
		
		// days
		if(daysBox.getChildren().stream().filter(a -> a instanceof CheckBox)
				.map(a -> (CheckBox)a).filter(a -> a.isSelected()).count() <= 0) {
			throw new StudentException("Please select days for the class.");
		}
		
	}
}
