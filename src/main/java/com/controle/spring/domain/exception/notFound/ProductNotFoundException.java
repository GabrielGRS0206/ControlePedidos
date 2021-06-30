package com.controle.spring.domain.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.controle.spring.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	public static final String MSG_PRODUCT_NOT_FOUND = "Mercadoria com código %d não encontrada ";

	public ProductNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ProductNotFoundException(Long id) {
		this(String.format(MSG_PRODUCT_NOT_FOUND, id));
	}

}
