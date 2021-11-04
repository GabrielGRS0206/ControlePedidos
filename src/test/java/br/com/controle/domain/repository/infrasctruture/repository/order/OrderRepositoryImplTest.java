/**
 * 
 */
package br.com.controle.domain.repository.infrasctruture.repository.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderRowMapper;
import br.com.controle.domain.utils.Utils;

/**
 * @author Gabriel Rocha Severino
 *
 */
class OrderRepositoryImplTest {

	@Mock
	public JdbcTemplate template;

	@InjectMocks
	OrderRepositoryImpl repositoryImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.repository.infrasctruture.repository.order.OrderRepositoryImpl#openOrders()}.
	 */
	@Test
	void testOpenOrders() {
		when(template.query(Utils.EMPTY, mock(OrderRowMapper.class))).thenReturn(Arrays.asList(new Order()));
		List<Order> list = repositoryImpl.openOrders();
		assertNotNull(list, "list is null");
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.repository.infrasctruture.repository.order.OrderRepositoryImpl#cancelOrder(long)}.
	 */
	@Test
	void testCancelOrder() {
		boolean cancel = repositoryImpl.cancelOrder(1l);
		assertEquals(cancel, true);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.repository.infrasctruture.repository.order.OrderRepositoryImpl#deleteItens(long)}.
	 */
	@Test
	void testDeleteItens() {
		boolean deleteItems = repositoryImpl.deleteItens(1l);
		assertEquals(deleteItems, true);
	}

}
