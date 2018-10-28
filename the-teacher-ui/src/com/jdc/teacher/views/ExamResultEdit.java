package com.jdc.teacher.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Popup;
import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.common.TeacherApplicationException;
import com.jdc.teacher.entity.ClassRoom.Grade;
import com.jdc.teacher.entity.ExamResult;
import com.jdc.teacher.entity.ExamResult.Exam;
import com.jdc.teacher.entity.Student;
import com.jdc.teacher.model.ClassRoomModel;
import com.jdc.teacher.model.ExamResultModel;
import com.jdc.teacher.model.StudentModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ExamResultEdit implements Initializable, Popup{

    @FXML
    private ComboBox<Grade> grade;

    @FXML
    private ComboBox<String> year;

    @FXML
    private ComboBox<Exam> exam;

    @FXML
    private ComboBox<Student> student;

    @FXML
    private TextField burmese;

    @FXML
    private TextField english;

    @FXML
    private TextField maths;
    
    private Reloader loader;
    
    private ClassRoomModel roomModel;
    private StudentModel studentModel;
    private ExamResultModel examModel;

    @FXML
    void save(ActionEvent event) {
    	try {
    		
    		ExamResult data = new ExamResult();
    		data.setStudent(student.getValue());
    		
    		if(null == data.getStudent()) {
    			throw new TeacherApplicationException("Please select student.");
    		}
    		
    		data.setExam(exam.getValue());
    		
    		if(null == data.getExam()) {
    			throw new TeacherApplicationException("Please select Exam");
    		}
    		
    		data.setBurmese(getInt(burmese));
    		data.setEnglish(getInt(english));
    		data.setMaths(getInt(maths));
    		
    		examModel.save(data);
    		
    		loader.search();
    		
    		exam.getScene().getWindow().hide();
			
		} catch (TeacherApplicationException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText(e.getMessage());
			alert.show();
		}

    }

	private int getInt(TextField textField) {
		
		try {
			return Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			throw new TeacherApplicationException("Please enter mark with digit.");
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		roomModel = new ClassRoomModel();
		studentModel = new StudentModel();
		examModel = new ExamResultModel();
		
		grade.getItems().addAll(Grade.values());
		exam.getItems().addAll(Exam.values());
		
		grade.valueProperty().addListener((a,b,c) -> loadYears());
		year.valueProperty().addListener((a,b,c) -> loadStudents());
		
	}

	@Override
	public void setLoader(Reloader reloader) {
		this.loader = reloader;
	}
	
	private void loadYears() {
		year.getItems().clear();
		
		if(null != grade.getValue()) {
			year.getItems().addAll(roomModel.findYearByGrade(grade.getValue()));
		}
	}
	
	private void loadStudents() {
		student.getItems().clear();
		
		if(null != grade.getValue() && null != year.getValue()) {
			List<Student> students = studentModel.find(grade.getValue(), year.getValue(), null);
			student.getItems().addAll(students);
		}
	}

}
