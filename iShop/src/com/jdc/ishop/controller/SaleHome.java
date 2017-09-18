package com.jdc.ishop.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.ishop.utils.Security;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class SaleHome implements Initializable {

    @FXML
    private StackPane contentView;
    @FXML
    private HBox pos;
    @FXML
    private HBox report;
    @FXML
    private HBox controleBox;
    @FXML
    private Label memberName;

    @FXML
    void close(MouseEvent event) {
    	contentView.getScene().getWindow().hide();
    }

    @FXML
    void showReport(MouseEvent event) {

    	try {
    		controleBox.getChildren().remove(report);
    		controleBox.getChildren().add(1, pos);
        	
       		loadView("SalePosReport.fxml");
       	 
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void showPos(MouseEvent event) {
    	
    	try {
    		controleBox.getChildren().remove(pos);
    		controleBox.getChildren().add(1, report);
    		
    		loadView("SalePos.fxml");
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    private Node oldView;
    
    private void loadView(String viewName) throws IOException {
    	
    	if(!contentView.getChildren().isEmpty()) {
    		oldView = contentView.getChildren().get(0);
    	}
    	
    	Parent view = FXMLLoader.load(getClass().getResource(viewName));
    	view.setLayoutY(638);
    	contentView.getChildren().add(view);
    	
    	TranslateTransition trans = new TranslateTransition(Duration.millis(800), view);
    	trans.setFromY(638);
    	trans.setToY(0);
    	trans.setCycleCount(1);
    	trans.setAutoReverse(false);
    	
    	trans.setOnFinished(e -> {
    		if(null != oldView) {
    			contentView.getChildren().remove(oldView);
    			oldView = null;
    		}
    	});
    	
    	trans.play();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controleBox.getChildren().remove(pos);
		controleBox.getChildren().remove(report);
		
		memberName.setText(Security.getLoginUser().getName());
		
		showPos(null);
	}

	public static void showView() {
		try {
			Parent root = FXMLLoader.load(SaleHome.class.getResource("SaleHome.fxml"));
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
