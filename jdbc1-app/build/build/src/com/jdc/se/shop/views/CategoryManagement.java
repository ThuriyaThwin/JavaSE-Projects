package com.jdc.se.shop.views;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.jdc.online.entity.Category;
import com.jdc.se.shop.MessageHandler;
import com.jdc.se.shop.ShopException;
import com.jdc.se.shop.custom.CategoryItem;
import com.jdc.se.shop.service.CategoryService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

public class CategoryManagement implements Initializable, Consumer<Category>{

    @FXML
    private FlowPane flowPane;

    @FXML
    private TextField name;
    
    private CategoryService service;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = CategoryService.getService();
		loadCategory();
		
	}

	private void loadCategory() {
		List<Category> list = service.getAll();
		flowPane.getChildren().clear();
		if(null != list && !list.isEmpty()) {
			List<CategoryItem> items = list.stream().map(c -> new CategoryItem(c, CategoryManagement.this::accept)).collect(Collectors.toList());
			flowPane.getChildren().addAll(items);
		}
	}

	public void addNew() {
	
		try {
			
			Category c = new Category();
			c.setName(name.getText());
			
			if(c.getName() == null || c.getName().isEmpty()) {
				throw new ShopException("Please enter Category Name.");
			}
			
			service.save(c);
			name.clear();
			
			loadCategory();
			
		} catch (ShopException e) {
			MessageHandler.handle(e);
		}
		
	}
	
	public void upload() {
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Category Upload File");
			
			File file = fc.showOpenDialog(flowPane.getScene().getWindow());
			
			if(null == file) {
				throw new ShopException("Please select a file to upload.");
			}
			
			Files.lines(file.toPath())
				.map(line -> {
					Category c = new Category();
					c.setName(line);
					return c;
				}).forEach(c -> {
					service.save(c);
				});
			
			loadCategory();
		} catch (ShopException e) {
			MessageHandler.handle(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void accept(Category t) {
		service.save(t);
		loadCategory();
	}
}
