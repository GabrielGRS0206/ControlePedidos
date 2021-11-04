package br.com.controle.domain.model;

public enum StatusOrder {

	OPEN("OPEN",1),
	PRODUCTION("PRODUCTION",2),
	FINISH("FINISH",3),
	CANCELED("CANCELED",4);

	private String description;
	private Integer cod;

	StatusOrder(String description, Integer code) {
		this.description = description;
		this.cod = code;
	}

	public static String valueOfPorCodigo(Integer value){
		String retorno = "";
		for(StatusOrder cod : StatusOrder.values()){
			if(cod.getCod().equals(value)){
				retorno = cod.getDescription();
				break;
			}
		}
		return retorno;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCod() {
		return cod;
	}
}
