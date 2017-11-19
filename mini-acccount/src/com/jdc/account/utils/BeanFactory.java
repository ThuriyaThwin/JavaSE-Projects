package com.jdc.account.utils;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class BeanFactory {

	private static Weld weld;
	private static WeldContainer container;
	
	static {
		weld = new Weld();
		container = weld.initialize();
	}
	
	public static<T> T getBean(Class<T> type) {
		return container.select(type).get();
	}
	
	public static void close() {
		if(null != weld)
			weld.shutdown();
	}
}
