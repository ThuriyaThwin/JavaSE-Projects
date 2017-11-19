package com.jdc.account.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewHelper {

	public static<T> Parent getView(String viewName, Class<T> type) throws IOException {
		FXMLLoader loader = new FXMLLoader(type.getResource(viewName));
		loader.setControllerFactory(BeanFactory::getBean);
		return loader.load();
	}
}
