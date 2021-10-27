package br.com.controle.domain.exception.business;

public enum MessageException {

	MSG_IMCOMPLET("Dados incompletos"), MSG_USUARIO_INVALIDO("Login inválido"), MSG_TOKEN_EXPIRADO("Token expirado"),
	MSG_TOKEN_INVALIDO("Token invalido"), MSG_CAIXA_NAO_ENCONTRADO("Caixa com código %d não encontrado"),
	MSG_CLIENTE_NAO_ENCONTRADO("Cliente com código %d não encontrado"),
	MSG_COMANDA_NAO_ENCONTRADA("Comanda com código %d não encontrado"),
	MSG_PRODUCT_NOT_FOUND("Produto com código %d não encontrado"),
	MSG_CASH_REGISTER_CLOSE("CashRegister com código %d já está com valor de fechamento"),
	MSG_USER_BLOCK("Usuário bloqueado"), OBJECT_NOT_NULL("Objeto não pode ser null"),
	MSG_INFORME_CASH_REGISTER("Deve ser informado um id de caixa");

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
