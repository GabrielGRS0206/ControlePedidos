package br.com.controle.api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.com.controle.api.model.dto.request.CashRegisterClosureRequestV1Dto;
import br.com.controle.api.model.dto.request.CashRegisterOpenRequestV1Dto;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.service.CashRegisterService;

class CashRegisterV1ControllerTest {

	@Mock
	private CashRegisterService service;

	@InjectMocks
	private CashRegisterV1Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSave() {
		when(service.save(Mockito.any())).thenReturn(new CashRegister(1l));
		CashRegisterOpenRequestV1Dto request = new CashRegisterOpenRequestV1Dto();
		request.setData(LocalDate.now());
		request.setTotal(BigDecimal.ONE);
		ResponseEntity<Object> reponse = controller.save(request);
		assertNotNull(reponse);
	}

	@Test
	final void testUpdate() {
		when(service.save(Mockito.any())).thenReturn(new CashRegister(1l));
		CashRegisterClosureRequestV1Dto request = new CashRegisterClosureRequestV1Dto();
		request.setTotal(BigDecimal.ONE);
		ResponseEntity<Object> reponse = controller.update(request, 1l);
		assertNotNull(reponse);
	}

}
