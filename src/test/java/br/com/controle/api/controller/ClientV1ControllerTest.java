package br.com.controle.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.com.controle.api.dto.response.ClientResponseV1Dto;
import br.com.controle.api.mapper.ClientV1Mapper;
import br.com.controle.domain.model.Client;
import br.com.controle.domain.service.ClientService;
import br.com.controle.domain.utils.Utils;

class ClientV1ControllerTest {

	@InjectMocks
	private ClientV1Controller controller;

	@Mock
	private ClientService service;

	@Mock
	private ClientV1Mapper mapper;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testSave() {
		when(service.save(Mockito.any())).thenReturn(new Client());
		when(mapper.toEntity(Mockito.any())).thenReturn(new Client());
		when(mapper.toDto(Mockito.any())).thenReturn(mock(ClientResponseV1Dto.class));

		ResponseEntity<Object> response = controller.save(Mockito.any());
		assertNotNull(response);
	}

	@Test
	void testFindAll() {
		when(service.findAll()).thenReturn(Arrays.asList(new Client()));
		when(mapper.listToDto(Mockito.anyList())).thenReturn(Arrays.asList(new ClientResponseV1Dto()));
		List<ClientResponseV1Dto> list = controller.findAll();
		assertNotNull(list);
	}

	@Test
	void testDelete() {
		controller.delete(1l);
		verify(service, times(1)).delete(1l);
	}

	@Test
	void testUpdate() {
		when(service.update(Mockito.any())).thenReturn(new Client());
		when(mapper.toEntity(Mockito.any())).thenReturn(new Client());
		when(mapper.toDto(Mockito.any())).thenReturn(mock(ClientResponseV1Dto.class));

		ResponseEntity<Object> response = controller.update(1l,Mockito.any());
		assertNotNull(response);
	}

	@Test
	final void testFindById() {
		when(service.findById(1l)).thenReturn(Optional.of(new Client()));
		when(mapper.toDto(Mockito.any())).thenReturn(Mockito.any());
		ResponseEntity<Object> response = controller.findById(1l);
		assertNotNull(response);
		verify(service, times(1)).findById(1l);
		verify(mapper, times(1)).toDto(Mockito.any());
	}

	@Test
	final void testFindByDocument() {
		when(service.findByDocument(Utils.EMPTY)).thenReturn(Optional.of(new Client()));
		when(mapper.toDto(Mockito.any())).thenReturn(Mockito.any());
		ResponseEntity<Object> response = controller.findByDocument(Utils.EMPTY);
		assertNotNull(response);
		verify(service, times(1)).findByDocument(Utils.EMPTY);
		verify(mapper, times(1)).toDto(Mockito.any());
	}

}
