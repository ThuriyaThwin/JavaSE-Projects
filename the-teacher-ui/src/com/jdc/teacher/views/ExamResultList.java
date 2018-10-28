package com.jdc.teacher.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.ExamResult;
import com.jdc.teacher.entity.ExamResult.Exam;
import com.jdc.teacher.model.ExamResultModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class ExamResultList implements Initializable, Reloader{

    @FXML
    private ComboBox<Grade> grade;
    @FXML
    private ComboBox<Exam> exam;

    @FXML
    private TextField year;

    @FXML
    private TextField student;

    @FXML
    private TableView<ExamResult> table;
    @FXML
    private TableColumn<ExamResult, Integer> colBurmese;

    @FXML
    private TableColumn<ExamResult, Integer> colEnglish;

    @FXML
    private TableColumn<ExamResult, Integer> colMaths;
   
    private ExamResultModel model;

    @FXML
    void clear(ActionEvent event) {
    	grade.setValue(null);
    	year.clear();
    	student.clear();
    }

    public void search() {
    	
    	List<ExamResult> list = model.find(grade.getValue(), exam.getValue(), year.getText(), student.getText());

    	table.getItems().clear();
    	table.getItems().addAll(list);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new ExamResultModel();
		grade.getItems().addAll(Grade.values());
		exam.getItems().addAll(Exam.values());
		
		table.setEditable(true);
		
		StringConverter<Integer> converter = new StringConverter<Integer>() {

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {
				
				try {
					return Integer.valueOf(string);
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setContentText("Please enter digit for marks.");
					alert.show();
				}
				
				return null;
			}
		};
		
		colBurmese.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		colBurmese.setOnEditCommit(event -> {
			Integer data = event.getNewValue();
			
			if(null != data) {
				event.getRowValue().setBurmese(data);
				model.update(event.getRowValue());
				table.refresh();
			}
		});

		colEnglish.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		colEnglish.setOnEditCommit(event -> {
			Integer data = event.getNewValue();
			
			if(null != data) {
				event.getRowValue().setEnglish(data);
				model.update(event.getRowValue());
				table.refresh();
			}
		});

		colMaths.setCellFactory(TextFieldTableCell.forTableColumn(converter));
		colMaths.setOnEditCommit(event -> {
			Integer data = event.getNewValue();
			
			if(null != data) {
				event.getRowValue().setMaths(data);
				model.update(event.getRowValue());
				table.refresh();
			}
		});
	}

}
