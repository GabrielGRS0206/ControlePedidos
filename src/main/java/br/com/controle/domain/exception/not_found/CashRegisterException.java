package br.com.controle.domain.exception.not_found;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.controle.domain.exception.business.EntityNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CashRegisterException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_CAIXA_NAO_ENCONTRADO = "Caixa com código %d não encontrado";

	public CashRegisterException(String mensagem) {
		super(mensagem);
	}

	public CashRegisterException(Long id) {
		this(String.format(MSG_CAIXA_NAO_ENCONTRADO, id));
	}
	
}
