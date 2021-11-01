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

import br.com.controle.api.mapper.OrderV1Mapper;
import br.com.controle.api.mapper.dto.request.OrderRequestV1Dto;
import br.com.controle.api.mapper.dto.response.OrderResponseV1Dto;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.service.OrderService;

class OrderV1ControllerTest {

	@Mock
	public OrderService service;

	@Mock
	private OrderV1Mapper mapper;

	@InjectMocks
	private OrderV1Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testSave() {
		when(mapper.dtoToEntity(Mockito.any())).thenReturn(mock(Order.class));
		when(mapper.entityToDto(mock(Order.class), OrderV1Mapper.SUMMARY)).thenReturn(mock(OrderResponseV1Dto.class));
		when(service.save(Mockito.any())).thenReturn(mock(Order.class));
		ResponseEntity<Object> response = controller.save(mock(OrderRequestV1Dto.class));
		assertNotNull(response);
		verify(mapper, times(1)).dtoToEntity(Mockito.any());
		verify(service, times(1)).save(Mockito.any());
	}

	@Test
	final void testFindAll() {
		List<Order> list = Arrays.asList(new Order());
		when(service.findAll()).thenReturn(list);
		when(mapper.listToDto(list, OrderV1Mapper.COMPLETE)).thenReturn(Arrays.asList(new OrderResponseV1Dto()));
		List<OrderResponseV1Dto> response = controller.findAllOpenOrders(1l);
		assertNotNull(response);
		verify(service, times(1)).openOrders();
	}

	@Test
	final void testCancel() {
		when(service.cancel(1l)).thenReturn(true);
		ResponseEntity<Object> response = controller.cancel(1l);
		assertNotNull(response);
	}

	@Test
	final void testUpdate() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testFindById() {
		Order order = new Order();
		when(service.findById(1l)).thenReturn(Optional.of(order));
		when(mapper.entityToDto(order, OrderV1Mapper.COMPLETE)).thenReturn(Mockito.any());
		ResponseEntity<Object> response = controller.findById(1l);
		assertNotNull(response);
		verify(service, times(1)).findById(1l);
		verify(mapper, times(1)).entityToDto(order, OrderV1Mapper.COMPLETE);
	}

}
