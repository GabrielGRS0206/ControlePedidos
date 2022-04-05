package br.com.controle.api.model.dto.request;

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

	public String getEmail() {
		return email.trim();
	}

	public String getPassword() {
		return password.trim();
	}

}
