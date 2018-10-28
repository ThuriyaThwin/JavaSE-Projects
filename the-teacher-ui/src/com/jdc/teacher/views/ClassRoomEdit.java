package com.jdc.teacher.views;

import java.net.URL;
import java.time.Year;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Popup;
import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.common.TeacherApplicationException;
import com.jdc.teacher.entity.ClassRoom;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.model.ClassRoomModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ClassRoomEdit implements Initializable, Popup {

    @FXML
    private ComboBox<Grade> grade;

    @FXML
    private TextField year;
    
    private ClassRoomModel model;
    
    private Reloader loader;

    @FXML
    void save(ActionEvent event) {
    	
    	try {
    		
    		ClassRoom room = new ClassRoom();
    		room.setGrade(grade.getValue());
    		room.setYear(year.getText());
    		
    		if(null == room.getGrade()) {
    			throw new TeacherApplicationException("Please select grade!");
    		}
    		
    		model.save(room);
    		
    		loader.search();
    		
    		year.getScene().getWindow().hide();
			
		} catch (TeacherApplicationException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(e.getMessage());
			alert.show();
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model = new ClassRoomModel();
		grade.getItems().addAll(Grade.values());
		year.setText(Year.now().toString());
	}

	@Override
	public void setLoader(Reloader reloader) {
		loader = reloader;
	}

}
