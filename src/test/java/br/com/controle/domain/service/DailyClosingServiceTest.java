/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.movement.DailyClosing;
import br.com.controle.domain.repository.infrasctruture.repository.dailyClosing.DailyClosingImpl;

/**
 * @author Gabriel Rocha Severino
 */
class DailyClosingServiceTest {

	@Mock
	public DailyClosingImpl repository;

	@Mock
	public CashRegisterService service;

	@InjectMocks
	private DailyClosingService serviceDailyClosingService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	final void testList() {

		when(service.existsById(1l)).thenReturn(true);
		when(repository.totalCashRegister(1l)).thenReturn(list());

		List<DailyClosing> list = serviceDailyClosingService.list(1l);
		assertEquals(list.size(), 3);
	}

	private List<DailyClosing> list() {
		List<DailyClosing> list = new ArrayList<DailyClosing>();
		list.add(new DailyClosing(1, BigDecimal.ZERO));
		list.add(new DailyClosing(1, BigDecimal.ZERO));
		list.add(new DailyClosing(1, BigDecimal.ZERO));
		return list;
	}
	
	@Test
	void testListThrows() {
		
		when(service.existsById(1l)).thenReturn(false);
		
		BusinessException exception = assertThrows(BusinessException.class, () ->{
			serviceDailyClosingService.list(1l);
		});
		assertNotNull(exception, "exception is null");
	}
}
