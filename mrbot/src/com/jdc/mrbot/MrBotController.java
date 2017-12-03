package com.jdc.mrbot;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MrBotController {

    @FXML
    private ListView<String> list;

    @FXML
    private TextField text;
    
    private MrBot bot;
    
    public MrBotController() {
    		bot = new MrBot();
	}
    
    public void ask() {
    		
    		String message = text.getText();
    		list.getItems().add(String.format("You > %s", message));
    		
    		String answer = bot.ask(message);
    		list.getItems().add(String.format("Bot > %s", answer));
    		text.clear();
    }

}
