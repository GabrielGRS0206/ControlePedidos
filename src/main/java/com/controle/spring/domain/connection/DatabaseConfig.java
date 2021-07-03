package com.controle.spring.domain.connection;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(LoadProperties.getDriverClassName());
		dataSource.setUrl(LoadProperties.getUrl());
		dataSource.setUsername(LoadProperties.getUser());
		dataSource.setPassword(LoadProperties.getPassword());
		
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