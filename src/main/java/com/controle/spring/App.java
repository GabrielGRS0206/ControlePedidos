package com.controle.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.controle.spring.domain.utils.SpringUtils;

@SpringBootApplication 
public class App {   

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		System.out.println(SpringUtils.replic("-",25));
		System.out.println(" SPRING BOOT.....");
		System.out.println(SpringUtils.replic("-",25)); 
	}
}
