package com.controle.spring.domain.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.controle.spring.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_COMANDA_NAO_ENCONTRADA = "Comanda com código %d não encontrado ";

	public OrderNotFoundException(String mensagem) {
		super(mensagem);
	}

	public OrderNotFoundException(Long id) {
		this(String.format(MSG_COMANDA_NAO_ENCONTRADA, id));
	}

}
