/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.model.Client;
import br.com.controle.domain.repository.ClientRepository;
import br.com.controle.domain.service.validation.DeleteClientValidation;

/**
 * @author Gabriel Rocha
 */
class ClientServiceTest {

	@Mock
	private ClientRepository repository;

	@Mock
	public DeleteClientValidation deleteValidation;

	@InjectMocks
	private ClientService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#save(java.lang.Object)}.
	 */
	@Test
	final void testSave() {

		assertThrows(BusinessException.class, () -> {
			Client entity = new Client();
			when(repository.save(any())).thenReturn(Optional.of(entity));
			when(repository.findByDocument(any())).thenReturn(Optional.of(new Client()));

			service.save(new Client());
		});
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#update(java.lang.Object)}.
	 */
	@Test
	final void testUpdate() {
		
		Client entity = new Client();
		when(repository.save(any())).thenReturn(Optional.of(entity));
		Client client = service.update(mock(Client.class));
		assertNotNull(client);
		verify(repository,times(1)).save(any());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#delete(long)}.
	 */
	@Test
	final void testDelete() {
		service.delete(1l);
		verify(deleteValidation, times(1)).delete(1l);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#findById(long)}.
	 */
	@Test
	final void testFindById() {

		when(repository.findById(any())).thenReturn(Optional.of(new Client()));

		Optional<Client> client = service.findById(1l);

		assertNotNull(client.get());
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#findAll()}.
	 */
	@Test
	final void testFindAll() {

		when(repository.findAll()).thenReturn(Arrays.asList(new Client()));

		List<Client> clients = service.findAll();

		assertNotNull(clients);
		assertEquals(clients.size(), 1);
	}
}
