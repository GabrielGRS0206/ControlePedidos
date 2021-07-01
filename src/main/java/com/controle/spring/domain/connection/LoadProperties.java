package com.controle.spring.domain.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	private LoadProperties(){
		throw new IllegalStateException("Utility class");
	}

	public static String getProperty(String property) {
		String value = null;
		Properties properties = new Properties();
		try {
			try (FileInputStream file = new FileInputStream("./src/main/resources/application.properties")) {
				properties.load(file);
				value = properties.getProperty(property);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String getDriverClassName() {
		return getProperty("spring.datasource.driver-class-name");
	}

	public static String getUrl() {
		return getProperty("spring.datasource.url");
	}

	public static String getPassword() {
		return getProperty("spring.datasource.password");
	}

	public static String getUser() {
		return getProperty("spring.datasource.username");
	}

}