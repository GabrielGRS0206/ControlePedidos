package br.com.controle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.api.config.security.JwtTokenUtil;
import br.com.controle.api.mapper.dto.request.UserRequestV1DTO;
import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.security.JwtToken;
import br.com.controle.domain.model.security.UserSystem;
import br.com.controle.domain.utils.CryptUtil;

@Service
public class AuthenticeService {

	private static final String BEARER = "Bearer ";
	private static final Integer ONE_TRY = 1;
	private static final Integer MAX_ERROR = 3;

	@Autowired
	private JwtTokenUtil serviceToken;

	@Autowired
	private UserService userService;

	public AuthenticeService(JwtTokenUtil tokenService) {
		this.serviceToken = tokenService;
	}

	public JwtToken authentice(UserRequestV1DTO request) {

		firstValidation(request);

		var token = new JwtToken();
		token.setType(BEARER);

		UserSystem user = (UserSystem) userService.loadUserByUsername(request.getEmail());

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

	private void blockedUser(UserSystem user) {
		user.setBlocked("S");
		userService.update(user);
	}

	private void firstValidation(UserRequestV1DTO request) {
		if (request.getEmail().equals("") || !request.getEmail().contains("@") || request.getPassword().equals("")) {
			throw new BusinessException(MessageException.DATA_IMCOMPLET.getValue());
		}
	}

	private boolean maxErrorPassword(UserSystem user) {
		return user.getPasswordError().equals(MAX_ERROR);
	}
}
