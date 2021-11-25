/**
 * 
 */
package br.com.controle.domain.service.validation;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.EntityInUseException;
import br.com.controle.domain.repository.ProductRepository;

class DeleteProductValidationTest {

	@Mock
	private ProductRepository repository;
	
	@InjectMocks
	private DeleteProductValidation validation;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.validation.DeleteProductValidation#delete(java.lang.Long)}.
	 */
	@Test
	final void testDelete() {
		validation.delete(1l);
		verify(repository, times(1)).deleteById(1l);
	}
	
	/**
	 * Test method for {@link br.com.controle.domain.service.validation.DeleteProductValidation#delete(java.lang.Long)}.
	 */
	@Test
	void testValidationThrowsExceptionEmptyResult() {
		Mockito.doThrow(new EmptyResultDataAccessException(0)).when(repository).deleteById(1l);
		
		Assertions.assertThrows(BusinessException.class, () -> {
			validation.delete(1l);
		});
		verify(repository, times(1)).deleteById(1l);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.validation.DeleteProductValidation#delete(java.lang.Long)}.
	 */
	@Test
	void testValidationThrowsExceptionDataIntegrity() {
		Mockito.doThrow(new DataIntegrityViolationException("")).when(repository).deleteById(1l);

		Assertions.assertThrows(EntityInUseException.class, () -> {
			validation.delete(1l);
		});
		verify(repository, times(1)).deleteById(1l);
	}
	

}
