package br.com.controle.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.api.mapper.dto.request.UserRequestDTO;
import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.TokenJwt;
import br.com.controle.domain.model.UserSystem;
import br.com.controle.domain.utils.CryptUtil;
import io.jsonwebtoken.Claims;

@Service
public class AuthenticeService {

	private static final String BEARER = "Bearer ";
	private static final Integer ONE_TRY = 1;
	private static final Integer MAX_ERROR = 3;

	@Autowired
	private TokenService serviceToken;

	@Autowired
	private UserService userService;

	public TokenJwt authentice(UserRequestDTO request) {

		firstValidation(request);

		var token = new TokenJwt();
		token.setType(BEARER);

		UserSystem user = userService.findByEmail(request.getEmail());

		boolean passwordOk = CryptUtil.pswOk(request.getPassword(), user.getPassword());

		if (user.isBlocked()) {
			throw new BusinessException(MessageException.MSG_USER_BLOCK.getValue());
		}

		if (passwordOk) {
			token.setToken(serviceToken.generateToken(user.getUsername()));
		} else {
			user.setPasswordError(user.getPasswordError() + ONE_TRY);

			if (maxErrorPassword(user)) {
				blockedUser(user);
			}
			throw new BusinessException(MessageException.MSG_USUARIO_INVALIDO.getValue());
		}
		return token;
	}

	private void blockedUser(UserSystem user) {
		user.setBlocked("S");
		userService.update(user);
	}

	private void firstValidation(UserRequestDTO request) {
		if (request.getEmail().equals("") || !request.getEmail().contains("@") || request.getPassword().equals("")) {
			throw new BusinessException(MessageException.MSG_IMCOMPLET.getValue());
		}
	}

	private boolean maxErrorPassword(UserSystem user) {
		return user.getPasswordError().equals(MAX_ERROR);
	}

	public boolean validaToken(String token) {
		try {
			String tokenAuthorization = token.replace(BEARER, "");
			Claims claims = serviceToken.decodeToken(tokenAuthorization);

			// Verifica se o token está expirado
			if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
				throw new BusinessException(MessageException.MSG_TOKEN_EXPIRADO.getValue());
			}
			return true;
		} catch (BusinessException et) {
			throw new BusinessException(MessageException.MSG_TOKEN_EXPIRADO.getValue());
		} catch (Exception e) {
			throw new BusinessException(MessageException.MSG_TOKEN_INVALIDO.getValue());
		}
	}
}
