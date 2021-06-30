package com.controle.spring.domain.utils.connection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.controle.spring.domain.utils.connection.configJdbc.ConnectionConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
public class DataSourceConnection extends HikariConfig{

	public static DataSource dataSource;
	public static HikariDataSource hikari;
	public ConnectionConfig connectionConfig;
	
	public DataSourceConnection() {
		super();
		connectionConfig = new ConnectionConfig();
	}
	
	@Bean
	public DataSource dataSource() throws SQLException {
		if(connectionConfig != null && connectionConfig.isOk()) {
			this.setUsername(connectionConfig.getUsername());
			this.setPassword(connectionConfig.getPassword());
			this.setDriverClassName(connectionConfig.getDriverClassName());
			this.setJdbcUrl(connectionConfig.getUrl());
			this.setPoolName("SpringPool");

			if(hikari == null) {
				hikari = new HikariDataSource(this);
			}
		}
		
		return hikari;
	}

	public static Connection getConnection() throws SQLException {

		Connection conn = null;

		if(dataSource == null) {
			try {
				DataSourceConnection jpa = new DataSourceConnection();
				dataSource = jpa.dataSource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		if(dataSource != null) {
			conn = dataSource.getConnection();
		}

		return conn;
	}

}
