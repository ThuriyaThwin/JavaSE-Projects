package com.jdc.ishop.utils;

import com.jdc.ishop.IShopException;

public interface MessageHandler {
	
	static MessageHandler getManagerHandler() {
		return ManagerMessage.getInstance();
	}
	
	void showMessage(IShopException e);
	void clear();
	void alert(IShopException e);
}
