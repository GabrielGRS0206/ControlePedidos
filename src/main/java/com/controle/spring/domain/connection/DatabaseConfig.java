package com.controle.spring.domain.connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	public static String driver = "";
	public static String url = "";
	public static String user = "";
	public static String password = "";

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		try {
			if(dataSource.getConnection() != null) {
				System.out.println("Connection ok..");
			}
		} catch (SQLException e) {
			System.out.println("Erro de conexão");
			e.printStackTrace();
		}
		return dataSource;
	}
}