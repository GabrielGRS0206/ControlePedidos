package com.controle.spring.domain.utils.enums;

public enum TypeMascara {

	CPF("###.###.###-##"),
	CNPJ("##.###.###/####-##"),
	CEP("#####-###");

	private String mask;

	private TypeMascara(String mascara) {
		this.mask = mascara;
	}

	public String getMask() {
		return mask;
	}

}
