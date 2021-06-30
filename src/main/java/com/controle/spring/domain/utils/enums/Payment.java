package com.controle.spring.domain.utils.enums;

public enum Payment {

	/**
	 * Sugestão de melhoria...
	 * Poderia ser uma tabela no banco de dados e isso ser cadastrado 
	 * no sistema,aqui como exemplo coloquei os principais
	*/
	DINHEIRO(1, "DINHEIRO"),
	CARTAO_CREDITO(2, "CARTÃO DE CRÉDITO"),
	CARTAO_DEBITO(3,"CARTÃO DE DEBITO"),
	BRINDE(4,"BRINDE"),
	PIX(5,"PIX"),
	OUTROS(6,"OUTROS");

	private Integer cod;
	private String description;

	private Payment(Integer cod, String descricao) {
		this.cod = cod;
		this.description = descricao;
	}
	public Integer getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}

}
