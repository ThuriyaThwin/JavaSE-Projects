package com.jdc.teacher.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.Student;
import com.jdc.teacher.model.StudentModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class StudentList implements Initializable, Reloader{

    @FXML
    private ComboBox<Grade> grade;

    @FXML
    private TextField year;

    @FXML
    private TextField student;

    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colPhone;

    @FXML
    private TableColumn<Student, String> colRemark;
    
    private StudentModel model;

    @FXML
    void clear(ActionEvent event) {
    	grade.setValue(null);
    	year.clear();
    	student.clear();
    	table.getItems().clear();
    }

    public void search() {
    	
    	List<Student> list = model.find(grade.getValue(), year.getText(), student.getText());
    	table.getItems().clear();
    	table.getItems().addAll(list);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new StudentModel();
		grade.getItems().addAll(Grade.values());
		
		table.setEditable(true);
		
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit(event -> {
			String newData = event.getNewValue();
			if(null != newData && !newData.isEmpty()) {
				event.getRowValue().setName(newData);
				model.update(event.getRowValue());
			}
		});
		
		colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		colPhone.setOnEditCommit(event -> {
			String newData = event.getNewValue();
			if(null != newData && !newData.isEmpty()) {
				event.getRowValue().setPhone(newData);
				model.update(event.getRowValue());
			}
		});

		colRemark.setCellFactory(TextFieldTableCell.forTableColumn());
		colRemark.setOnEditCommit(event -> {
			String newData = event.getNewValue();
			if(null != newData && !newData.isEmpty()) {
				event.getRowValue().setRemark(newData);
				model.update(event.getRowValue());
			}
		});
	}

}
