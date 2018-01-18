package com.jdc.se.shop.views;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.jdc.online.entity.Category;
import com.jdc.online.entity.Product;
import com.jdc.se.shop.MessageHandler;
import com.jdc.se.shop.ShopException;
import com.jdc.se.shop.custom.ProductItem;
import com.jdc.se.shop.service.CategoryService;
import com.jdc.se.shop.service.ProductService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

public class ProductManagement implements Initializable, Consumer<Product> {

	@FXML
	private ListView<Category> categories;

	@FXML
	private FlowPane produdtContainer;
	
	private ProductService productService;
	private CategoryService catService;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		productService = ProductService.getService();
		catService = CategoryService.getService();
		
		// load category
		categories.getItems().addAll(catService.getAll());
		
		// load product
		if(categories.getItems().size() > 0) {
			categories.getSelectionModel().select(0);
		}

		categories.getSelectionModel().selectedItemProperty().addListener((a,b,c) -> {
			loadProducts();
		});

		loadProducts();
	}

	private void loadProducts() {
		try {
			Category c = categories.getSelectionModel().getSelectedItem();
			
			if(c == null) {
				throw new ShopException("Please select category.");
			}

			List<Product> products = productService.findByCategory(c);
			
			List<ProductItem> list = products.stream().map(p -> new ProductItem(p, ProductManagement.this))
				.collect(Collectors.toList());
			
			produdtContainer.getChildren().clear();
			produdtContainer.getChildren().addAll(list);
			
		} catch (ShopException e) {
			MessageHandler.handle(e);
		}
		
	}

	public void addNew() {
		
		try {
			Category c = categories.getSelectionModel().getSelectedItem();
			
			if(c == null) {
				throw new ShopException("Please select category.");
			}
			
			ProductAdd.showView(c, this);
			
		} catch (ShopException e) {
			MessageHandler.handle(e);
		}
	}

	public void upload() {
		try {
			Category c = categories.getSelectionModel().getSelectedItem();
			
			if(null == c) {
				throw new ShopException("Please select category.");
			}
			
			FileChooser fc = new FileChooser();
			fc.setTitle("Select Upload File");
			
			File file = fc.showOpenDialog(categories.getScene().getWindow());
			
			if(null == file) {
				throw new ShopException("Please select file to upload.");
			}
			
			List<Product> products = Files.lines(file.toPath())
					.map(line -> line.split("\t"))
					.filter(array -> array.length == 2)
					.map(array -> {
						Product p = new Product();
						p.setName(array[0]);
						p.setPrice(Integer.parseInt(array[1]));
						return p;
					}).collect(Collectors.toList());
			
			productService.save(products);
			
			loadProducts();
		} catch (IOException e) {
			throw new ShopException("Please check file layout.");
		} catch (ShopException e) {
			MessageHandler.handle(e);
		}
	}

	@Override
	public void accept(Product t) {
		productService.save(t);
		loadProducts();
	}
}
