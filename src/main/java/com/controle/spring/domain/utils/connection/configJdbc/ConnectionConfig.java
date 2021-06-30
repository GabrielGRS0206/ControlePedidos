package com.controle.spring.domain.utils.connection.configJdbc;

import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
public class ConnectionConfig {

	private String username;
	private String password;
	private String driverClassName;
	private String url;

	public ConnectionConfig() {
		this.username = "root";
		this.password = "master";
		this.driverClassName = "org.mariadb.jdbc.Driver";
		this.url = "jdbc:mariadb://localhost:3306/banco_controle?useSSL=false";
	}

	public boolean isOk() {
		if(username != null
				&& password != null
				&& driverClassName != null
				&& url != null) {
			return true;
		}

		return false;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
