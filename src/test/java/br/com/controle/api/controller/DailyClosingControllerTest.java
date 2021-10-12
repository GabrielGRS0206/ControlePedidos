package br.com.controle.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.com.controle.domain.movement.DailyClosing;
import br.com.controle.domain.service.DailyClosingService;

public class DailyClosingControllerTest {

	@Mock
	private DailyClosingService service;

	@InjectMocks
	private DailyClosingController controller;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testTotaisCashRegister() {

		when(service.list(1l)).thenReturn(Arrays.asList(new DailyClosing()));

		ResponseEntity<Object> response = controller.totaisCashRegister(1l);
		assertNotNull(response);
		verify(service, times(1)).list(1l);
	}

}
