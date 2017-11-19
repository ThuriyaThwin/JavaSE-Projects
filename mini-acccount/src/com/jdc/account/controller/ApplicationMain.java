package com.jdc.account.controller;

import com.jdc.account.utils.BeanFactory;
import com.jdc.account.utils.EntityManagerProducer;
import com.jdc.account.utils.ViewHelper;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = ViewHelper.getView("CategoryList.fxml", CategoryList.class);
		stage.setScene(new Scene(root));
		
		stage.setOnHiding(event -> {
			EntityManagerProducer.close();
			BeanFactory.close();
		});
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
