package com.controle.spring.domain.service.validation;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.controle.spring.domain.repository.ClientRepository;

@Component
public class DeleteClientValidationTest {

	@Mock
	private ClientRepository repository;

	@InjectMocks
	private DeleteClientValidation validation;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Valida delete client")
	public void deleteValidationClient() {
		Long id = 1L;

		validation.delete(id);
		verify(repository,times(1)).deleteById(id);
	}

	@Test
	@DisplayName("Captura ClientNotFoundException")
	public void exceptionClientNotFoundException() {

		doThrow(EmptyResultDataAccessException.class);
		try {
			validation.delete(1L);
		} catch (Exception e) {}
		finally {
			Mockito.verifyNoInteractions(repository);
		}
	}


	@Test
	@DisplayName("Captura exception EntityInUseException")
	public void exceptionEntityInUseException() {

		doThrow(DataIntegrityViolationException.class);
		try {
			validation.delete(1L);
			Mockito.verifyNoInteractions(repository);
		} catch (Exception e) {}
		finally {
			Mockito.verifyNoInteractions(repository);
		}
	}

}
