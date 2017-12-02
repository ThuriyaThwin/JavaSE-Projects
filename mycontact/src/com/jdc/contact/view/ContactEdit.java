package com.jdc.contact.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.contact.model.Contact;
import com.jdc.contact.model.Contact.Group;
import com.jdc.contact.model.ContactRepo;
import com.jdc.contact.model.ContactRepoMemory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ContactEdit implements Initializable{

    @FXML
    private ComboBox<Group> group;

    @FXML
    private TextField name;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private TextArea address;
    
    private ContactReloader reloader;
    private ContactRepo storage;

    public void close() {
    		email.getScene().getWindow().hide();
    }

    public void save() {
    		// get view data
    		Contact contact = getViewData();
    		
    		// save to the storage
    		storage.add(contact);
    		
    		// reload data
    		reloader.reload();
    	
    		// close 
    		close();
    }

	private Contact getViewData() {
		Contact c = new Contact();
		c.setGroup(group.getValue());
		c.setName(name.getText());
		c.setPhone(phone.getText());
		c.setEmail(email.getText());
		c.setAddress(address.getText());
		return c;
	}

	public static void show(ContactReloader reloader) {
		
		try {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			
			FXMLLoader loader = new FXMLLoader(ContactEdit.class.getResource("ContactEdit.fxml"));
			Parent root = loader.load();
			ContactEdit controller = loader.getController();
			controller.reloader = reloader;
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		storage = ContactRepoMemory.getInstance();
		group.getItems().addAll(Group.values());
	}


}
