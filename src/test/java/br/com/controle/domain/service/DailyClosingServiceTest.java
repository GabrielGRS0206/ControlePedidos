/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.DailyClosingService#list(long)}.
	 */
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

}
