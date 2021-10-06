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

import br.com.controle.domain.repository.ClientRepository;

/**
 * @author gabriel
 * Gabriel Rocha
 */
class DeleteClientValidationTest {

	@Mock
	private ClientRepository repository;
	
	@InjectMocks
	private DeleteClientValidation validation;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for {@link br.com.controle.domain.service.validation.DeleteClientValidation#delete(java.lang.Long)}.
	 */
	@Test
	final void testDelete() {
		validation.delete(1l);
		verify(repository,times(1)).deleteById(1l);
	}

}
