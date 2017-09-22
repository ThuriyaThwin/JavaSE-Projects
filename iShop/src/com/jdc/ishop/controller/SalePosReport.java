package com.jdc.ishop.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.entity.Category;
import com.jdc.ishop.model.entity.Invoice;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.model.service.InvoiceService;
import com.jdc.ishop.utils.PosMessage;
import com.jdc.ishop.utils.Security;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

public class SalePosReport implements Initializable {

    @FXML
    private ComboBox<Category> schCategory;

    @FXML
    private DatePicker schDateFron;

    @FXML
    private DatePicker schDateTo;

    @FXML
    private TableView<Invoice> table;
    
    private CategoryService catService;
    private InvoiceService invService;

    @FXML
    void clear(ActionEvent event) {
    	schCategory.setValue(null);
    	schDateFron.setValue(LocalDate.now().minusMonths(1));
    	schDateTo.setValue(LocalDate.now());
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		catService = CategoryService.getInstance();
		invService = InvoiceService.getInstance();
		
		schCategory.getItems().addAll(catService.find(false));
		
		schCategory.valueProperty().addListener((a,b,c) -> search());
		schDateFron.valueProperty().addListener((a,b,c) -> search());
		schDateTo.valueProperty().addListener((a,b,c) -> search());
		
		search();
	}

	public void search() {
		
		try {
			
			List<Invoice> list = invService.find(Security.getLoginUser().getLogin(), schCategory.getValue(), 
					schDateFron.getValue(), schDateTo.getValue());
			table.getItems().clear();
			table.getItems().addAll(list);
			
		} catch (IShopException e) {
			PosMessage.getInstance().showMessage(e);
		}
	}

}
