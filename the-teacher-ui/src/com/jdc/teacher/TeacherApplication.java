package com.jdc.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.teacher.common.Popup;
import com.jdc.teacher.common.Reloader;
import com.jdc.teacher.views.ClassRoomList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TeacherApplication implements Initializable{

	@FXML
	private Label title;

	@FXML
	private StackPane contentView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showClassRooms(null);
	}


	@FXML
	void showClassRooms(MouseEvent event) {
		loadView("Class Room Management", "ClassRoomList.fxml");
	}

	@FXML
	void showExamResults(MouseEvent event) {
		loadView("Exam Result Management", "ExamResultList.fxml");
	}

	@FXML
	void showStudents(MouseEvent event) {
		loadView("Student Management", "StudentList.fxml");
	}

	@FXML
	void addExamResult(MouseEvent event) {
		loadView("Exam Result Management", "ExamResultList.fxml", "ExamResultEdit.fxml");
	}

	@FXML
	void addNewClassRoom(MouseEvent event) {
		loadView("Class Room Management", "ClassRoomList.fxml", "ClassRoomEdit.fxml");
	}

	@FXML
	void addNewStudent(MouseEvent event) {
		loadView("Student Management", "StudentList.fxml", "StudentEdit.fxml");
	}

	private void loadView(String title, String view, String popup) {
		loadView(title, view);
		
		try {
			
			FXMLLoader loader = new FXMLLoader(ClassRoomList.class.getResource(popup));
			Parent layout = loader.load();
			Popup popupView = loader.getController();

			FXMLLoader cLoader = new FXMLLoader(ClassRoomList.class.getResource(view));
			Parent cView = cLoader.load();
			Reloader reloader = cLoader.getController();
			popupView.setLoader(reloader);
			
			contentView.getChildren().clear();
			contentView.getChildren().add(cView);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(layout));
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadView(String title, String view) {

		this.title.setText(title);

		try {
			Parent layout = FXMLLoader.load(ClassRoomList.class.getResource(view));
			contentView.getChildren().clear();
			contentView.getChildren().add(layout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
