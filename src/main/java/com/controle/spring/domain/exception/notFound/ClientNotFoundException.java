package com.controle.spring.domain.exception.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.controle.spring.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_CLIENTE_NAO_ENCONTRADO = "Cliente com código %d não encontrado";

	public ClientNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ClientNotFoundException(Long id) {
		this(String.format(MSG_CLIENTE_NAO_ENCONTRADO, id));
	}

}
