package com.jdc.collection;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CollectionController implements Initializable {

	@FXML
	private ComboBox<String> comb1;
	@FXML
	private ComboBox<DayOfWeek> comb2;
	@FXML
	private ComboBox<Course> comb3;
	
	@FXML
	private Label lab1;
	@FXML
	private Label lab2;
	@FXML
	private Label lab3;
	
	public void show() {
		String str1 = comb1.getValue();
		lab1.setText(str1);
		
		DayOfWeek day = comb2.getValue();
		if(null != day) {
			lab2.setText(day.toString());
		}
		
		Course course = comb3.getValue();
		if(null != course) {
			lab3.setText(course.getName());
		}
	}
	
	public void clear() {
		comb1.setValue(null);
		comb2.setValue(null);
		comb3.setValue(null);
		lab1.setText("");
		lab2.setText("");
		lab3.setText("");
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		comb1.getItems().add("Hello");
		comb1.getItems().add("Java");
		comb1.getItems().add("Android");
		comb1.getItems().add("Spring");
		
		comb2.getItems().addAll(DayOfWeek.values());
		
		comb3.getItems()
			.addAll(CourseModel.getModel().getCourses());
	
		clear();
	}

}
