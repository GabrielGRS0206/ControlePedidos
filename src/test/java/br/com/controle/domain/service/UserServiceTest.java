/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.model.security.Permission;
import br.com.controle.domain.model.security.UserSystem;
import br.com.controle.domain.repository.UserRepository;

/**
 * @author Gabriel Rocha Severino
 *
 */
class UserServiceTest {

	private static final String EMAIL = "test@gmail.com";
	@Mock
	private UserRepository repository;

	@Mock
	private PermissionService permissionService;

	@InjectMocks
	private UserService userService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.UserService#loadUserByUsername(java.lang.String)}.
	 */
	@Test
	final void testLoadUserByUsername() {

		UserSystem user = new UserSystem();
		user.setId(1l);
		when(repository.findByEmail(EMAIL)).thenReturn(Optional.of(user));
		when(permissionService.findPermissionUserId(1l)).thenReturn(Arrays.asList(new Permission()));
			
		UserDetails userDetails = userService.loadUserByUsername(EMAIL);
		assertNotNull(userDetails, "user is null");
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.UserService#loadUserByUsername(java.lang.String)}.
	 */
	@Test
	final void testLoadUserByUsernameThrowsException() {

		when(repository.findByEmail(EMAIL)).thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () ->{
			userService.loadUserByUsername(EMAIL);
		});
		assertNotNull(exception, "exception is null");
	}
	
	@Test
	final void testUpdateUser() {
		UserSystem user = new UserSystem();
		user.setId(1l);
		userService.update(user);
		verify(repository, times(1)).save(user);
	}
	
}
