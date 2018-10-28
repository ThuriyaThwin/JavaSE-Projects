package com.jdc.se.shop.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.online.entity.Category;
import com.jdc.online.entity.Product;
import com.jdc.se.shop.service.CategoryService;
import com.jdc.se.shop.service.ProductService;
import com.jdc.se.shop.service.vo.ProductVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class NewOrder implements Initializable {

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TableView<ProductVO> productTable;
    
    private CategoryService catService;
    private ProductService productService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		catService = CategoryService.getService();
		productService = ProductService.getService();
		
		category.getItems().addAll(catService.getAll());
		
		category.valueProperty().addListener((a, b, c) -> loadProduct());
		
		if(category.getItems().size() > 0) {
			category.getSelectionModel().select(0);
		}
	}

	private void loadProduct() {		
		productTable.getItems().clear();
		List<ProductVO> list = productService.findVoByCategory(category.getValue());
		productTable.getItems().addAll(list);
	}

}
