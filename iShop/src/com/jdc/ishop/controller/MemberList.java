package com.jdc.ishop.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jdc.ishop.model.entity.Member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemberList implements Initializable, Consumer<Member> {

    @FXML
    private ComboBox<?> schRole;

    @FXML
    private TextField schName;

    @FXML
    private TableView<Member> table;

    @FXML
    void addNew(ActionEvent event) {
    	MemberEdit.showView(null, this);
    }

    @FXML
    void clear(ActionEvent event) {
    	schRole.setValue(null);
    }

	@Override
	public void accept(Member t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
