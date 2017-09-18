package com.jdc.ishop.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.jdc.ishop.IShopException;
import com.jdc.ishop.model.service.CategoryService;
import com.jdc.ishop.model.service.ItemService;
import com.jdc.ishop.model.service.MemberService;
import com.jdc.ishop.model.service.OrderService;
import com.jdc.ishop.utils.ManagerMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Home implements Initializable{

    @FXML
    private BarChart<String, Integer> chart;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Label members;

    @FXML
    private Label categories;

    @FXML
    private Label items;
    
    private OrderService orderService;
    private MemberService memService;
    private CategoryService catService;
    private ItemService itemService;

    @FXML
    void reset(ActionEvent event) {
    	dateTo.setValue(LocalDate.now());
    	dateFrom.setValue(LocalDate.now().minusMonths(1));
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		orderService = OrderService.getInstance();
		memService = MemberService.getInstance();
		catService = CategoryService.getInstance();
		itemService = ItemService.getInstance();
		
		reset(null);
		
		long memberCount = memService.findCount();
		long categoryCount = catService.findCount();
		long itemCount = itemService.findCount();
		
		members.setText(String.valueOf(memberCount));
		categories.setText(String.valueOf(categoryCount));
		items.setText(String.valueOf(itemCount));
		
		dateFrom.valueProperty().addListener((a,b,c) -> loadChart());
		dateTo.valueProperty().addListener((a,b,c) -> loadChart());
		
		loadChart();
	}

	private void loadChart() {
		try {
			Map<String, Map<String, Integer>> data = orderService.findData(dateFrom.getValue(), dateTo.getValue());
			chart.getData().clear();
			
			
			for(Entry<String, Map<String, Integer>> e : data.entrySet()) {
				XYChart.Series<String, Integer> series = new Series<>();
				
				series.setName(e.getKey());
				
				for (Entry<String, Integer> d : e.getValue().entrySet()) {
					series.getData().add(new XYChart.Data<String, Integer>(d.getKey(), d.getValue()));
				}
				
				chart.getData().add(series);
				
			}
			
		} catch (IShopException e) {
			ManagerMessage.getInstance().showMessage(e);
		}
	}

}
