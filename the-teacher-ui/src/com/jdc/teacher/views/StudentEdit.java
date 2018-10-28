package com.jdc.teacher.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Popup;
import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.common.TeacherApplicationException;
import com.jdc.teacher.entity.ClassRoom;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.Student;
import com.jdc.teacher.model.ClassRoomModel;
import com.jdc.teacher.model.StudentModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class StudentEdit implements Initializable, Popup{

    @FXML
    private ComboBox<Grade> grade;

    @FXML
    private ComboBox<String> year;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextArea remark;
    
    private Reloader loader;
    
    private StudentModel studentModel;
    private ClassRoomModel classModel;

    @FXML
    void save(ActionEvent event) {
    	try {
    		
    		ClassRoom room = new ClassRoom();
    		room.setGrade(grade.getValue());
    		room.setYear(year.getValue());
    		
    		if(null == room.getGrade()) {
    			throw new TeacherApplicationException("Please select grade!");
    		}
    		
    		if(room.getYear().isEmpty()) {
    			throw new TeacherApplicationException("Please select Year!");
    		}
    		
    		Student s = new Student();
    		s.setRoom(room);
    		
    		s.setName(name.getText());
    		if(s.getName().isEmpty()) {
    			throw new TeacherApplicationException("Please enter Student Name!");
    		}
    		
    		s.setPhone(phone.getText());
    		if(s.getPhone().isEmpty()) {
    			throw new TeacherApplicationException("Please enter Contact Phone!");
    		}

    		s.setRemark(remark.getText());
    		
    		studentModel.save(s);
    		
    		loader.search();
    		
    		grade.getScene().getWindow().hide();
			
		} catch (TeacherApplicationException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(e.getMessage());
			alert.show();
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		studentModel = new StudentModel();
		classModel = new ClassRoomModel();
		
		grade.getItems().addAll(Grade.values());
		
		grade.valueProperty().addListener((a,b,c) -> {
			year.getItems().clear();;
			if(null != grade.getValue()) {
				List<String> years = classModel.findYearByGrade(grade.getValue());
				year.getItems().addAll(years);
			}
		});
		
	}

	@Override
	public void setLoader(Reloader reloader) {
		this.loader = reloader;
	}

}
