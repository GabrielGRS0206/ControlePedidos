/**
 * 
 */
package br.com.controle.domain.service.validation;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.repository.ProductRepository;

/**
 * @author gabriel
 * Gabriel Rocha
 */
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

}
