package com.controle.spring.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

	protected ResponseEntity<Object> ok(Object body) {
		return ResponseEntity.ok(body);
	}
	
	protected ResponseEntity<Object> created(Object body) {
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	
	protected ResponseEntity<Object> notFound() {
		return ResponseEntity.notFound().build();
	}
}
