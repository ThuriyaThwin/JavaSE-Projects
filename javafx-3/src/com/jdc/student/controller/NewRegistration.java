package com.jdc.student.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.student.StudentException;
import com.jdc.student.model.ClassModel;
import com.jdc.student.model.RegistrationModel;
import com.jdc.student.model.entity.Registration;
import com.jdc.student.model.entity.Student;
import com.jdc.student.model.vo.ClassVO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewRegistration implements Initializable{

    @FXML
    private ComboBox<ClassVO> classCombo;

    @FXML
    private Label startDate;

    @FXML
    private Label days;

    @FXML
    private Label times;

    @FXML
    private Label fees;

    @FXML
    private TextField studentName;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextField paid;
    
    private Student selectedStudent;

    @FXML
    void clear(ActionEvent event) {
    	classCombo.setValue(null);
    	startDate.setText("");
    	days.setText("");
    	times.setText("");
    	fees.setText("");
    	
    	studentName.clear();
    	phone.clear();
    	email.clear();
    	paid.clear();
    }

    @FXML
    void save(ActionEvent event) {
    	
    	try {
    		
    		// check
    		check();
    		
    		if(null == selectedStudent) {
    			selectedStudent = new Student();
    			selectedStudent.setName(studentName.getText());
    			selectedStudent.setPhone(phone.getText());
    			selectedStudent.setEmail(email.getText());
    		}
    		
    		Registration regis = new Registration();
    		regis.setClassId(classCombo.getValue().getClassData().getId());
    		regis.setFees(classCombo.getValue().getCourse().getFees());
    		regis.setPaid(Integer.parseInt(paid.getText()));
    		
			regModel.create(regis, selectedStudent);
			
			MainFrame.getController().navigate("Registration List");
			
		} catch (StudentException e) {
			e.printStackTrace();
		}
    	
    }
    
    private void check() {
    	// class 
    	if(null == classCombo.getValue()) {
    		throw new StudentException("Please select a Class!");
    	}
    	
    	// name
    	if(null == studentName.getText() || studentName.getText().isEmpty()) {
    		throw new StudentException("Please enter student name!");
    	}
    	
    	// phone
    	if(null == phone.getText() || phone.getText().isEmpty()) {
    		throw new StudentException("Please enter student Phone Number!");
    	}
    	
    	// paid
    	if(null == paid.getText() || paid.getText().isEmpty()) {
    		throw new StudentException("Please enter Paid Amount!");
    	}
    	
    	try {
			Integer.parseInt(paid.getText());
		} catch (NumberFormatException e) {
    		throw new StudentException("Please enter Paid Amount with Digit!");
		}
    	
	}

	private ClassModel classModel;
    private RegistrationModel regModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classModel = new ClassModel();
		regModel = new RegistrationModel();
		
		List<ClassVO> list = classModel.find(null, LocalDate.now().minusWeeks(2));
		classCombo.getItems().addAll(list);
		
		classCombo.valueProperty().addListener((a,b,c) -> {
			ClassVO vo = classCombo.getValue();
			startDate.setText(vo.getStartDate());
			days.setText(vo.getDays());
			times.setText(vo.getTime());
			fees.setText(vo.getFees());
		});
		
		clear(null);
	}

}
