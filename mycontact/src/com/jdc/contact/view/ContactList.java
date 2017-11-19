package com.jdc.contact.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.contact.model.Contact;
import com.jdc.contact.model.ContactStorage;
import com.jdc.contact.model.Contact.Group;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ContactList implements Initializable, ContactReloader{

    @FXML
    private ComboBox<Group> groups;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TableView<Contact> table;
    
    private ContactStorage storage;

    public void addContact() {
    		ContactEdit.show(this);
    }

    public void clear() {
    		groups.setValue(null);
    		name.clear();
    		phone.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		storage = ContactStorage.getInstance();
		groups.getItems().addAll(Group.values());
		
		groups.valueProperty().addListener(new ChangeListener<Group>() {

			@Override
			public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
				reload();
			}
		});
		
		name.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				reload();
			}
		});
		
		phone.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				reload();
			}
		});
		
		reload();
	}

	@Override
	public void reload() {
		table.getItems().clear();
		List<Contact> list = storage.search(groups.getValue(), name.getText(), phone.getText());
		table.getItems().addAll(list);
	}

}
