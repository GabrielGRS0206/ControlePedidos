package com.controle.spring.domain.utils.enums;

public enum TypeMascara {

	CPF("###.###.###-##"),
	CNPJ("##.###.###/####-##"),
	CEP("#####-###");

	private String mascara;

	private TypeMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getMascara() {
		return mascara;
	}

}
