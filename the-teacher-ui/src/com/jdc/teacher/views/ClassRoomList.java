package com.jdc.teacher.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jdc.teacher.common.ClassRoomItem;
import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.entity.ClassRoom;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.model.ClassRoomModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class ClassRoomList implements Initializable, Reloader{

    @FXML
    private ComboBox<Grade> grade;

    @FXML
    private TextField year;

    @FXML
    private FlowPane container;
    
    private ClassRoomModel model;

    @FXML
    void clear(ActionEvent event) {
    	grade.setValue(null);
    	year.clear();
    	container.getChildren().clear();
    }

    public void search() {
    	List<ClassRoom> list = model.find(grade.getValue(), year.getText());
    	container.getChildren().clear();
    	container.getChildren()
    		.addAll(list.stream()
    				.map(ClassRoomItem::new)
    				.collect(Collectors.toList()));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model = new ClassRoomModel();
		
		grade.getItems().addAll(Grade.values());
		
	}


}
