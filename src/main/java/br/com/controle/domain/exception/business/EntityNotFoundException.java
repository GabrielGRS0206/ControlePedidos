package br.com.controle.domain.exception.business;

public abstract class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	protected EntityNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}
