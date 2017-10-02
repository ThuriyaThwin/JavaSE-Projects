package com.jdc.flag.view;

import java.io.File;
import java.io.FileInputStream;

import com.jdc.flag.game.FlagGame;
import com.jdc.flag.game.Question;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GameView {

    @FXML
    private ImageView flag;

    @FXML
    private TilePane questions;
    
    private FlagGame game;

	public static Parent getView(FlagGame currentGame) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(GameView.class.getResource("GameView.fxml"));
			Parent view = loader.load();
			
			GameView controller = loader.getController();
			controller.game = currentGame;
			
			controller.loadNewQuestion();
			
			return view;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void loadNewQuestion() {
		
		try {
			Question q = game.next();
			
			if(null != q) {
				// new question
				flag.setImage(new Image(new FileInputStream(new File("flags", q.getFlag().getImage()))));
				questions.getChildren().clear();
				
				for(String str : q.getNames()) {
					JFXButton btn = new JFXButton(str);
					questions.getChildren().add(btn);
					btn.setOnAction(this::answer);
				}
			} else {
				// no question
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void answer(ActionEvent e) {
		
		JFXButton btn = (JFXButton) e.getSource();
		String answer = btn.getText();
		
		if(game.check(answer)) {
			// right
			
		} else {
			// wrong
			
		}
		
	}

}
