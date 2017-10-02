package com.jdc.flag.view;

import com.jdc.flag.game.GameController;
import com.jdc.flag.game.PlayerService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class Setting {

	@FXML
	private VBox box;

	@FXML
	private ToggleGroup countGroup;

	@FXML
	void save(ActionEvent event) {
		RadioButton radio = box.getChildren().stream().filter(a -> a instanceof RadioButton)
				.map(a -> (RadioButton)a)
				.filter(a -> a.isSelected())
				.findAny().orElse(null);
		
		if(null != radio) {
			switch (radio.getText().toLowerCase()) {
			case "five":
				controller.getCurrentPlayer().setQuestionCount(5);
				break;
			case "seven":
				controller.getCurrentPlayer().setQuestionCount(7);
				break;
			case "ten":
				controller.getCurrentPlayer().setQuestionCount(10);
				break;

			default:
				break;
			}
			
			PlayerService.getService().save();
			controller.start();
		}
	}

	private GameController controller;

	public static Parent getView(GameController controller) {
		
		try {
			FXMLLoader loader = new FXMLLoader(Setting.class.getResource("Setting.fxml"));
			Parent view = loader.load();
			Setting setting = loader.getController();
			setting.controller = controller;
			return view;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
