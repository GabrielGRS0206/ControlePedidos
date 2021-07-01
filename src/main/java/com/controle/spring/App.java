package com.controle.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.controle.spring.domain.connection.DatabaseConfig;
import com.controle.spring.domain.connection.LoadProperties;
import com.controle.spring.domain.utils.SpringUtils;

@SpringBootApplication 
public class App {   

	public static void main(String[] args) {
		
		DatabaseConfig.driver = LoadProperties.getDriverClassName();
		DatabaseConfig.url = LoadProperties.getUrl();
		DatabaseConfig.user = LoadProperties.getUser();
		DatabaseConfig.password = LoadProperties.getPassword();
		
		SpringApplication.run(App.class, args);

		System.out.println(SpringUtils.replic("-",25));
		System.out.println(" SPRING BOOT.....");
		System.out.println(SpringUtils.replic("-",25));
	}
}
