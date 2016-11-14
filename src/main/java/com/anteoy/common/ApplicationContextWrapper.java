package com.anteoy.common;

import org.springframework.web.context.WebApplicationContext;

public class ApplicationContextWrapper {

	private static WebApplicationContext applicationContext;

	public static void setApplicationContext(WebApplicationContext webApplicationContext) {
		applicationContext = webApplicationContext;
	}

	public static WebApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

}
