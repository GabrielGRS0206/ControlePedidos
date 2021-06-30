package com.controle.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.controle.spring.domain.utils.SpringUtils;
import com.controle.spring.domain.utils.connection.ConnectionJdbc;

@SpringBootApplication 
public class App {   

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		System.out.println(SpringUtils.replic("-",25));
		System.out.println(" SPRING BOOT.....");
		System.out.println(SpringUtils.replic("-",25));

		if(ConnectionJdbc.getConexao() != null) {
			System.out.println("Conexão JDBC Ok");
		} else {
			System.err.print("Verifique dados da Conexão JDBC na class ConnectionConfig");
		}
	} 

}
