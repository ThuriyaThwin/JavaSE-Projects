package com.jdc.flag.view;

import com.jdc.flag.game.Player;
import com.jdc.flag.game.PlayerService;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

public class GameHome {

    @FXML
    private TableView<Player> table;
 
	public static Parent getView() {
		try {
			
			FXMLLoader loader = new FXMLLoader(GameHome.class.getResource("GameHome.fxml"));
			Parent view = loader.load();
			
			GameHome controller = loader.getController();
			controller.table.getItems().clear();
			controller.table.getItems().addAll(PlayerService.getService().getTopPlayers());
			
			return view;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
