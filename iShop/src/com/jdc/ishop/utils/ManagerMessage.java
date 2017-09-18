package com.jdc.ishop.utils;

import com.jdc.ishop.IShopException;

import javafx.scene.control.Label;

public class ManagerMessage implements MessageHandler {
	
	private static ManagerMessage HANDLER;
	
	public static ManagerMessage getInstance() {
		return HANDLER;
	}
	
	public static void init(Label message) {
		HANDLER = new ManagerMessage(message);
	}
	
	private ManagerMessage(Label message) {
		this.message = message;
	}

	private Label message;

	@Override
	public void showMessage(IShopException e) {
		message.setText(e.getMessage());
	}

	@Override
	public void clear() {
		message.setText("");
	}

	@Override
	public void alert(IShopException e) {
		MessageView.showMessage(e.getMessage());
	}
}
