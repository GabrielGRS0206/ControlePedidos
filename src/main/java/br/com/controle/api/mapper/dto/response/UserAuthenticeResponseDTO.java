package br.com.controle.api.mapper.dto.response;

import br.com.controle.domain.model.TokenJwt;

public class UserAuthenticeResponseDTO {
	private String token;
	private String type;

	public UserAuthenticeResponseDTO(TokenJwt user) {
		this.token = user.getToken();
		this.type = user.getType();
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
