package com.jdc.student.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.student.model.ClassModel;
import com.jdc.student.model.CourseModel;
import com.jdc.student.model.entity.Course;
import com.jdc.student.model.vo.ClassVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class ClassList implements Initializable {
	
	@FXML
	private ComboBox<Course> courseCombo;
	@FXML
	private DatePicker startDate;
	
	@FXML
	private TableView<ClassVO> table;
	
	private ClassModel model;
	private CourseModel courseModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseModel = new CourseModel();
		model = new ClassModel();
		
		courseCombo.getItems().addAll(courseModel.findAll());
		
		courseCombo.valueProperty().addListener((a,b,c) -> search());
		startDate.valueProperty().addListener((a,b,c) -> search());
		search();
	}
	
	private void search() {
		table.getItems().clear();
		table.getItems().addAll(model.find(courseCombo.getValue(), startDate.getValue()));
	}

}
