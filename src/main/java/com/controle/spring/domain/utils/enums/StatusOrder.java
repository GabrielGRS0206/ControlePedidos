package com.controle.spring.domain.utils.enums;

public enum StatusOrder {

	ABERTA("ABERTA",1),
	PRODUCAO("EM PRODUÇÃO",2),
	FINALIZADA("FINALIZADA",3),
	CANCELADA("CANCELADA",4);

	private String descricao;
	private Integer value;

	StatusOrder(String descricao, Integer value) {
		this.descricao = descricao;
		this.value = value;
	}

	public static String valueOfPorCodigo(Integer value){
		String retorno = "";
		for(StatusOrder cod : StatusOrder.values()){
			if(cod.getValue().equals(value)){
				retorno = cod.getDescricao();
				break;
			}
		}
		return retorno;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}


	public String getDescricao() {
		return descricao;
	}
}
