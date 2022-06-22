/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.config.security.JwtTokenUtil;
import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.model.security.JwtToken;
import br.com.controle.domain.model.security.UserSystem;

/**
 * @author Gabriel Rocha Severino
 *
 */
class AuthenticeServiceTest {


	private static final String EMAIL = "test@gmail.com";
	private static final String PASSWORD = "202cb962ac59075b964b07152d234b7012563985646545";//123
	private static final String PASSWORD_DECRIPTADA = "123";
	private static final String PASSWORD_ERROR = "XXXX";

	@Mock
	private JwtTokenUtil serviceToken;

	@Mock
	private UserService userService;
	
	@InjectMocks
	private AuthenticeService authenticeService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeService() {
		
		when(userService.loadUserByUsername(EMAIL)).thenReturn(userMock());
		
		JwtToken token = authenticeService.authentice(EMAIL, PASSWORD_DECRIPTADA);
		assertNotNull(token, "token is null");
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeServicehThrowsLoginInvalido() {
		
		when(userService.loadUserByUsername(EMAIL)).thenReturn(userMock());
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			authenticeService.authentice(EMAIL, PASSWORD);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeServicehThrowsFirstValidation() {
		
		when(userService.loadUserByUsername(EMAIL)).thenReturn(userMock());
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			authenticeService.authentice(EMAIL.replaceAll("@", "-"), PASSWORD);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeServicehThrowsUserBlock() {
		
		UserSystem user = userMock();
		user.setBlocked("S");
		when(userService.loadUserByUsername(EMAIL)).thenReturn(user);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			authenticeService.authentice(EMAIL, PASSWORD);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeServicehThrowsPasswordFalse() {
		
		UserSystem user = userMock();
		user.setBlocked("S");
		when(userService.loadUserByUsername(EMAIL)).thenReturn(user);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			authenticeService.authentice(EMAIL, PASSWORD_ERROR);
		});
		assertNotNull(exception, "exception is null");
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.AuthenticeService#AuthenticeService(JwtTokenUtil)}.
	 */
	@Test
	final void testAuthenticeServicehThrowsMaxErrorPassword() {
		
		UserSystem user = userMock();
		user.setPasswordError(2);
		when(userService.loadUserByUsername(EMAIL)).thenReturn(user);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			authenticeService.authentice(EMAIL, PASSWORD_ERROR);
		});
		assertNotNull(exception, "exception is null");
	}
	
	private UserSystem userMock() {
		var user = new UserSystem();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		user.setBlocked("N");
		return user;
	}
}
