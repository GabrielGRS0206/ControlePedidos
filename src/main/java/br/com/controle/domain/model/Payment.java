package br.com.controle.domain.model;

public enum Payment {

	A_VISTA(1, "A VISTA","A_VISTA"),
	CARTAO_CREDITO(2, "CARTÃO DE CRÉDITO","CARTAO_CREDITO"),
	CARTAO_DEBITO(3,"CARTÃO DE DEBITO","CARTAO_DEBITO"),
	BRINDE(4,"BRINDE","BRINDE"),
	PIX(5,"PIX","PIX"),
	OUTROS(6,"OUTROS","OUTROS");

	private Integer cod;
	private String description;
	private String valueBd;

	private Payment(Integer cod, String descricao,String valueBd) {
		this.cod = cod;
		this.description = descricao;
		this.valueBd = valueBd;
	}
	
	public static String descriptionCod(Integer value){
		String retorno = "";
		for(Payment cod : Payment.values()){
			if(cod.getCod().equals(value)){
				retorno = cod.getDescription();
				break;
			}
		}
		return retorno;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}

	public String getValueBd() {
		return valueBd;
	}

	public void setValueBd(String valueBd) {
		this.valueBd = valueBd;
	}
}
