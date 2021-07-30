package com.controle.spring.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.controle.spring.domain.model.Client;
import com.controle.spring.domain.repository.ClientRepository;
import com.controle.spring.domain.service.validation.DeleteClientValidation;

public class ClientServiceTest {

	@Mock
	private ClientRepository repository;

	@Mock
	private DeleteClientValidation validation;

	@InjectMocks
	private ClientService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.service = new ClientService(repository, validation);
	}

	@Test
	@DisplayName("Save client")
	public void saveClient() {

		Client client = new Client();
		client.setName("name");
		client.setDocument("11122255588");
		client.setContact("48988447788");
		client.setCep("88960000");
		client.setNumber("50");
		client.setComplement("complement");
		client.setProximity("proximity");
		client.setObservation("observation");
		client.setStreet("street");
		client.setDistrict("district");
		client.setCity("city");	
		
		service.save(client);
		
		Mockito.verify(repository, times(1)).findByDocument(client.getDocument());
		
		assertNotNull(client);
		assertEquals(client.getName(), "name");
		assertEquals(client.getDocument(), "11122255588");
		assertEquals(client.getContact(), "48988447788");
		assertEquals(client.getCep(), "88960000");
		assertEquals(client.getNumber(), "50");
		assertEquals(client.getComplement(), "complement");
		assertEquals(client.getProximity(), "proximity");
		assertEquals(client.getObservation(), "observation");
		assertEquals(client.getStreet(), "street");
		assertEquals(client.getDistrict(), "district");
		assertEquals(client.getCity(), "city");
	}
	
	@Test
	@DisplayName("Captura exception BusinessException")
	public void clientIsPresentException() {

		Client client = new Client(1l);
		
		try {
			service.save(client);
		} catch (Exception e) {}
		finally {
			Mockito.verifyNoInteractions(service.save(Mockito.any()));
		}
		
	}
	
	
	
	
	
	
	
	
}
