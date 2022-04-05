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

import br.com.controle.api.model.dto.response.ProductResponseV1Dto;
import br.com.controle.api.model.mapper.ProductV1Mapper;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.service.ProductService;

class ProductV1ControllerTest {

	@Mock
	public ProductService service;

	@Mock
	public ProductV1Mapper mapper;

	@InjectMocks
	private ProductV1Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testSave() {
		when(service.save(Mockito.any())).thenReturn(new Product(1l));
		when(mapper.toEntity(Mockito.any())).thenReturn(new Product(1l));
		when(mapper.toDto(Mockito.any())).thenReturn(mock(ProductResponseV1Dto.class));

		ResponseEntity<Object> response = controller.save(Mockito.any());
		assertNotNull(response);
		verify(service, times(1)).save(Mockito.any());
		verify(mapper, times(1)).toDto(Mockito.any());
		verify(mapper, times(1)).toEntity(Mockito.any());
	}

	@Test
	void testFindAll() {
		when(mapper.listToDto(Mockito.anyList())).thenReturn(Arrays.asList(new ProductResponseV1Dto()));
		when(service.findAll()).thenReturn(Arrays.asList(new Product()));
		List<ProductResponseV1Dto> list = controller.findAll();
		assertNotNull(list);
	}

	@Test
	void testDelete() {
		controller.delete(1l);
		verify(service, times(1)).delete(1l);
	}

	@Test
	final void testUpdate() {
		when(service.update(Mockito.any())).thenReturn(new Product(1l));
		when(mapper.toEntity(Mockito.any())).thenReturn(new Product(1l));
		when(mapper.toDto(Mockito.any())).thenReturn(mock(ProductResponseV1Dto.class));

		ResponseEntity<Object> response = controller.update(1l, Mockito.any());
		assertNotNull(response);
		verify(service, times(1)).update(Mockito.any());
		verify(mapper, times(1)).toDto(Mockito.any());
		verify(mapper, times(1)).toEntity(Mockito.any());
	}

	@Test
	final void testFindById() {
		when(service.findById(1l)).thenReturn(Optional.of(new Product()));
		when(mapper.toDto(Mockito.any())).thenReturn(Mockito.any());
		ResponseEntity<Object> response = controller.findById(1l);
		assertNotNull(response);
		verify(service, times(1)).findById(1l);
		verify(mapper, times(1)).toDto(Mockito.any());
	}

}
