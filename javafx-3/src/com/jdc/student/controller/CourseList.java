package com.jdc.student.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.student.model.CourseModel;
import com.jdc.student.model.entity.Course;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class CourseList implements Initializable {

	@FXML
	private TableView<Course> table;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CourseModel model = new CourseModel();
		table.getItems().addAll(model.findAll());
	}

}
