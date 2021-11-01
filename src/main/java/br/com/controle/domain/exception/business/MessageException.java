package br.com.controle.domain.exception.business;

public enum MessageException {

	DATA_IMCOMPLET("Dados incompletos"), 
	INVALID_USER("Login inválido"), 
	TOKEN_EXPIRED("Token expirado"),
	TOKEN_INVALID("Token invalido"), 
	CASH_REGISTER_NOT_FOUND("Caixa com código %d não encontrado"),
	CLIENT_ID_NOT_FOUND("Cliente com código %d não encontrado"),
	CLIENT_REGISTERED("Cliente já cadastrado,código : %d"),
	CLIENT_DOCUMENT_NOT_FOUND("Cliente com CPF/CNPJ %d não encontrado"),
	ORDER_NOT_FOUND("Ordem com código %d não encontrada"),
	PRODUCT_ID_NOT_FOUND("Produto com código %d não encontrado"),
	CASH_REGISTER_CLOSE("CashRegister com código %d já está com valor de fechamento"),
	USER_BLOCK("Usuário bloqueado"), OBJECT_NOT_NULL("Objeto não pode ser null"),
	REPORT_CASH_REGISTER("Deve ser informado um id de caixa"),
	ERROR_DAILY_CLOSING("Erro ao consultar dados");

	private String value;

	MessageException(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
