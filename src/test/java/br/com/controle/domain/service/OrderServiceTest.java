/**
 * 
 */
package br.com.controle.domain.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
		Product product = new Product(1l);
		product.setUnitPrice(BigDecimal.ONE);

		when(cashRegisterService.findById(1l)).thenReturn(Optional.of(new CashRegister(1l)));
		when(orderItemRepository.saveAll(any())).thenReturn(items());
		when(productRepository.findById(1l)).thenReturn(Optional.of(product));
		// ==================

		Order order = new Order();
		order.setIdOrder(1l);
		order.setCashRegisterId(1l);
		order.setItems(items());

		Order entity = service.update(order);

		assertNotNull(entity);
		
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#findById(long)}.
	 */
	@Test
	final void testFindById() {
		Order order = new Order();
		when(repository.findById(1l)).thenReturn(Optional.of(order));
		Optional<Order> entity = service.findById(1l);

		assertNotNull(entity.get());
		verify(repository, times(1)).findById(1l);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#findAll()}.
	 */
	@Test
	final void testFindAll() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Order()));
		List<Order> list = service.findAll();
		assertNotNull(list);
		assertEquals(list.size(), 1);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#existsById(long)}.
	 */
	@Test
	final void testExistsById() {
		when(repository.existsById(1l)).thenReturn(true);
		boolean exists = service.existsById(1l);
		assertEquals(exists, true);
		verify(repository, times(1)).existsById(1l);
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#openOrder()}.
	 */
	@Test
	final void testOpenOrder() {
		when(repository.openOrders()).thenReturn(Arrays.asList(new Order()));
		List<Order> list = service.openOrders();
		assertEquals(list.size(), 1);
		verify(repository, times(1)).openOrders();
	}

	/**
	 * Test method for
	 * {@link br.com.controle.domain.service.OrderService#cancel(java.lang.Long)}.
	 */
	@Test
	final void testCancel() {
		when(repository.existsById(1l)).thenReturn(true);
		when(repository.cancelOrder(1l)).thenReturn(true);
		boolean cancel = service.cancel(1l);
		assertEquals(cancel, true);
	}

}
