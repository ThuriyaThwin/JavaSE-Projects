package com.jdc.flag;

import com.jdc.flag.game.Player;
import com.jdc.flag.view.GameFrame;
import com.jdc.flag.view.Login;
import com.jdc.flag.view.SignUp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameApplication extends Application {
	
	private static Stage STAGE;

	@Override
	public void start(Stage stage) throws Exception {
		STAGE = stage;
		
		showLoginView();
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void showLoginView() {
		HBox view = Login.getView();
		STAGE.setScene(new Scene(view));
	}
	
	public static void showSignView() {
		HBox view = SignUp.getView();
		STAGE.setScene(new Scene(view));
	}
	
	public static void showGameView(Player player) {
		STAGE.hide();
		STAGE = new Stage(StageStyle.UNDECORATED);
		HBox view = GameFrame.getView(player);
		STAGE.setScene(new Scene(view));
		STAGE.show();
	}

}
