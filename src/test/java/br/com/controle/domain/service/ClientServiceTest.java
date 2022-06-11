/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

class ClientServiceTest {

	private static final String DOCUMENT = "11122255588";

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
		
		when(repository.save(any())).thenReturn(new Client(1l));
		when(repository.findByDocument(DOCUMENT)).thenReturn(Optional.empty());

		Client client = new Client();
		client.setDocument(DOCUMENT);
		Client obj = service.save(client);
		assertNotNull(obj, "client is null");
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#save(java.lang.Object)}.
	 */
	@Test
	final void testSaveThrows() {
		
		when(repository.findByDocument(DOCUMENT)).thenReturn(Optional.of(new Client(1l)));

		Client client = new Client();
		client.setDocument(DOCUMENT);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			 service.save(client);
		});
		assertNotNull(exception, "exception");
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
	 * {@link br.com.controle.domain.service.ClientService#findByDocument(String)}.
	 */
	@Test
	final void testFindByDocument() {

		when(repository.findByDocument(any())).thenReturn(Optional.of(new Client()));

		Optional<Client> client = service.findByDocument(DOCUMENT);

		assertNotNull(client.get());
	}
	
	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#findByDocument(String)}.
	 */
	@Test
	final void testFindByDocumentThrows() {

		when(repository.findByDocument(any())).thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () ->{
			service.findByDocument(DOCUMENT);
		});
		assertNotNull(exception, "exception is null");
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
		assertEquals(1, clients.size());
	}
	
	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.ClientService#findByDocument(String)}.
	 */
	@Test
	final void testFindByIdtThrows() {

		when(repository.findById(any())).thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () ->{
			service.findById(1l);
		});
		assertNotNull(exception, "exception is null");
	}
}
