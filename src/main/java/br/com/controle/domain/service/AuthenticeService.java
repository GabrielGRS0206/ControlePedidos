package br.com.controle.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.api.config.security.JwtTokenUtil;
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
	private JwtTokenUtil serviceToken;

	@Autowired
	private UserService userService;

	/**
	 * @param tokenService
	 */
	public AuthenticeService(JwtTokenUtil tokenService) {
		this.serviceToken = tokenService;
	}

	public TokenJwt authentice(UserRequestDTO request) {

		firstValidation(request);

		var token = new TokenJwt();
		token.setType(BEARER);

//		UserSystem user = new UserSystem();//userService.findByEmail(request.getEmail());

		UserSystem user = mockUser();

		boolean passwordOk = CryptUtil.passwordOk(request.getPassword(), user.getPassword());

		if (user.isBlocked()) {
			throw new BusinessException(MessageException.USER_BLOCK.getValue());
		}

		if (passwordOk) {
			token.setToken(serviceToken.generateToken(user.getUsername()));
		} else {
			user.setPasswordError(user.getPasswordError() + ONE_TRY);

			if (maxErrorPassword(user)) {
				blockedUser(user);
			}
			throw new BusinessException(MessageException.INVALID_USER.getValue());
		}
		return token;
	}

	/**
	 * @return
	 */
	private UserSystem mockUser() {
		UserSystem user = new UserSystem();
		user.setPassword("202cb962ac59075b964b07152d234b7012563985646545");
		user.setId(1l);
		user.setEmail("teste@gmail.com");
		return user;
	}

	private void blockedUser(UserSystem user) {
		user.setBlocked("S");
		userService.update(user);
	}

	private void firstValidation(UserRequestDTO request) {
		if (request.getEmail().equals("") || !request.getEmail().contains("@") || request.getPassword().equals("")) {
			throw new BusinessException(MessageException.DATA_IMCOMPLET.getValue());
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
				throw new BusinessException(MessageException.TOKEN_EXPIRED.getValue());
			}
			return true;
		} catch (BusinessException et) {
			throw new BusinessException(MessageException.TOKEN_EXPIRED.getValue());
		} catch (Exception e) {
			throw new BusinessException(MessageException.TOKEN_INVALID.getValue());
		}
	}
}
