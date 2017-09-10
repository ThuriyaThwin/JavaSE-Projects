package com.jdc.collection;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	@FXML
	private ListView<Course> list1;
	@FXML
	private ListView<String> list2;
	
	@FXML
	private TableView<Course> table;
	@FXML
	private TableColumn<Course, String> nameColumn; 
	
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
		
		// Combo Box
		comb1.getItems().add("Hello");
		comb1.getItems().add("Java");
		comb1.getItems().add("Android");
		comb1.getItems().add("Spring");
		
		comb2.getItems().addAll(DayOfWeek.values());
		
		comb3.getItems()
			.addAll(CourseModel.getModel().getCourses());
	
		clear();
		
		// List View
		list1.getItems().addAll(CourseModel.getModel().getCourses());
		list1.setOnMouseClicked(event -> {
			Course c = list1.getSelectionModel().getSelectedItem();
			list2.getItems().add(c.getName());
		});
		
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(event -> {
			int index = list2.getSelectionModel().getSelectedIndex();
			if(index >= 0) {
				list2.getItems().remove(index);
			}
		});
		
		list2.setContextMenu(new ContextMenu(delete));
		
		// table view
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		table.getItems().addAll(CourseModel.getModel().getCourses());
	}

}
