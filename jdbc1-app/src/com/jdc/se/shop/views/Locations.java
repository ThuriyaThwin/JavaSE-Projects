package com.jdc.se.shop.views;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.jdc.online.entity.State;
import com.jdc.online.entity.Township;
import com.jdc.se.shop.ShopException;
import com.jdc.se.shop.custom.TownshipItem;
import com.jdc.se.shop.service.LocationService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

public class Locations implements Initializable {

    @FXML
    private ListView<State> states;

    @FXML
    private FlowPane townships;

    private LocationService service;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		service = LocationService.getService();
		
		loadState();
		
		states.getSelectionModel().selectedItemProperty().addListener((a,b,c) -> {
			loadTownship();
		});
		
		if(!states.getItems().isEmpty()) {
			states.getSelectionModel().select(0);
		}
	}
	
	private void loadTownship() {
		State state = states.getSelectionModel().getSelectedItem();
		if(null != state) {
			List<Township> list = service.findTownshipByState(state);
			townships.getChildren().clear();
			townships.getChildren().addAll(list.stream().map(TownshipItem::new).collect(Collectors.toList()));
		}
	}

	private void loadState() {
		states.getItems().clear();
		states.getItems().addAll(service.getAllState());
	}

	public void uploadState() {
		
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select State Upload File");
			File file = fc.showOpenDialog(states.getScene().getWindow());
			
			if(null != file) {
				List<State> list = Files.lines(file.toPath()).map(a -> {
					State state = new State();
					state.setName(a);
					return state;
				}).collect(Collectors.toList());
				
				service.uploadStates(list);
				
				loadState();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void uploadTownship() {
		
		try {
			
			State state = states.getSelectionModel().getSelectedItem();
			
			if(null == state) {
				throw new ShopException("Please select one state to upload townships.");
			}
			
			FileChooser fc = new FileChooser();
			fc.setTitle("Select Township Upload File");
			File file = fc.showOpenDialog(states.getScene().getWindow());
			
			if(null != file) {
				List<Township> list = Files.lines(file.toPath()).map(a -> {
					Township township = new Township();
					township.setName(a);
					township.setStateId(state.getId());
					return township;
				}).collect(Collectors.toList());
				
				service.uploadTownships(list);
				
				loadTownship();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
