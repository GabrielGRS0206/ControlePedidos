/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.model.Product;
import br.com.controle.domain.repository.OrderItemRepository;
import br.com.controle.domain.repository.OrderRepository;
import br.com.controle.domain.repository.ProductRepository;

/**
 * @author Gabriel Rocha Severino
 */
class OrderServiceTest {

	@Mock
	public OrderRepository repository;

	@Mock
	public OrderItemRepository orderItemRepository;

	@Mock
	public ProductRepository productRepository;

	@Mock
	public CashRegisterService cashRegisterService;

	@InjectMocks
	private OrderService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#save(java.lang.Object)}.
	 */
	@Test
	final void testSave() {

		Product product = new Product(1l);
		product.setUnitPrice(BigDecimal.ONE);
		
		when(cashRegisterService.findById(1l)).thenReturn(Optional.of(new CashRegister(1l)));
		when(orderItemRepository.saveAll(any())).thenReturn(items());
		when(productRepository.findById(1l)).thenReturn(Optional.of(product));
		// ==================

		Order order = new Order();
		order.setCashRegisterId(1l);
		order.setItems(items());

		Order entity = service.save(order);
		
		assertNotNull(entity);

	}

	private List<OrderItem> items() {
		OrderItem item = new OrderItem();
		item.setPrice(BigDecimal.ONE);
		item.setQuantity(BigDecimal.ONE);
		item.setAdditional(BigDecimal.ZERO);
		
		Product product = new Product();
		product.setId(1l);
		product.setUnitPrice(BigDecimal.ONE);
		item.setProduct(product);
		
		return Arrays.asList(item);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#update(java.lang.Object)}.
	 */
	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#delete(long)}.
	 */
	@Test
	final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#findById(long)}.
	 */
	@Test
	final void testFindById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#findAll()}.
	 */
	@Test
	final void testFindAll() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#existsById(long)}.
	 */
	@Test
	final void testExistsById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#openOrder()}.
	 */
	@Test
	final void testOpenOrder() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#cancel(java.lang.Long)}.
	 */
	@Test
	final void testCancel() {
		fail("Not yet implemented"); // TODO
	}

}
