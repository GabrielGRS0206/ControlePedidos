package br.com.controle;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class App {   

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
	}
}
