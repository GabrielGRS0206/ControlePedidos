package br.com.controle.domain.model;

public enum StatusOrder {

	ABERTA("ABERTA",1),
	PRODUCAO("EM PRODUÇÃO",2),
	FINALIZADA("FINALIZADA",3),
	CANCELADA("CANCELADA",4);

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
