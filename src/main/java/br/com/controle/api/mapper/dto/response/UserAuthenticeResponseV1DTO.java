package br.com.controle.api.mapper.dto.response;

import br.com.controle.domain.model.JwtToken;

public class UserAuthenticeResponseV1DTO {
	private String token;
	private String type;

	public UserAuthenticeResponseV1DTO(JwtToken user) {
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
