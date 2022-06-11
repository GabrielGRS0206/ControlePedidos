package br.com.controle.api.model.dto.response;

import br.com.controle.domain.model.security.JwtToken;
import lombok.Data;

@Data
public class UserAuthenticeResponseV1DTO extends BaseResponseDto {
	private String token;
	private String type;

	public UserAuthenticeResponseV1DTO(JwtToken user) {
		this.token = user.getToken();
		this.type = user.getType();
	}
}
