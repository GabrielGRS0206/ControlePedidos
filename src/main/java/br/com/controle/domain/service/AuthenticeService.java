package br.com.controle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import br.com.controle.config.security.JwtTokenUtil;
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

	public JwtToken authentice(String email,String password) {

		firstValidation(email,password);

		var token = new JwtToken();
		token.setType(BEARER);

		UserSystem user = (UserSystem) userService.loadUserByUsername(email);

		boolean passwordOk = CryptUtil.passwordOk(password, user.getPassword());

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

	private void firstValidation(String email,String password) {
		if (Strings.isNullOrEmpty(email) || !email.contains("@") || Strings.isNullOrEmpty(password)) {
			throw new BusinessException(MessageException.DATA_IMCOMPLET.getValue());
		}
	}

	private boolean maxErrorPassword(UserSystem user) {
		return user.getPasswordError().equals(MAX_ERROR);
	}
}
