package com.controle.spring.domain.exception.exceptionHandler;

public enum ErrorType {

	ERRO_DE_SISTEMA("/erro-de-sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso"),
	ERRO_NEGOCIO("/erro-negocio");

	private String title;

	ErrorType(String title) {
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
