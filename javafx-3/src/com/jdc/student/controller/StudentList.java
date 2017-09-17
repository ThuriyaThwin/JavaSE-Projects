package com.jdc.student.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.student.model.StudentModel;
import com.jdc.student.model.entity.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentList implements Initializable{

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TableView<Student> table;
    
    private StudentModel model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new StudentModel();
		
		name.textProperty().addListener((a,b,c) -> search());
		phone.textProperty().addListener((a,b,c) -> search());
		
		search();
	}

	private void search() {
		List<Student> students = model.search(name.getText(), phone.getText());
		table.getItems().clear();
		table.getItems().addAll(students);
	}

}
