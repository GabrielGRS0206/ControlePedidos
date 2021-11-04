/**
 * 
 */
package br.com.controle.domain.repository.infrasctruture.repository.dailyClosing;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.controle.domain.model.Payment;
import br.com.controle.domain.movement.DailyClosing;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 *
 */
class DailyClosingImplTest {

	@Mock
	JdbcTemplate template;

	@InjectMocks
	DailyClosingImpl repository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.repository.infrasctruture.repository.dailyClosing.DailyClosingImpl#totalCashRegister(long)}.
	 */
	@Test
	void testTotalCashRegister() {
		when(template.queryForList(Utils.EMPTY)).thenReturn(mock());
		List<DailyClosing> list = repository.totalCashRegister(1l);
		assertNotNull(list, "list is null");
	}

	/**
	 * @return
	 */
	
	private List<Map<String, Object>> mock() {
		Map<String, Object> map = new HashMap<>();
		map.put(Payment.A_VISTA.getDescription(), BigDecimal.ONE);
		List<Map<String, Object>> oo = new ArrayList<Map<String,Object>>();
		oo.add(map);
		return oo;
	}

}
