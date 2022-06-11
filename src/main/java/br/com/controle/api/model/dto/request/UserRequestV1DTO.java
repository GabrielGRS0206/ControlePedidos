package br.com.controle.api.model.dto.request;

import lombok.Data;

@Data
public class UserRequestV1DTO {

	private String email;
	private String password;

	public UserRequestV1DTO() {
		super();
	}

	public UserRequestV1DTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
