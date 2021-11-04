package br.com.controle.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.repository.OrderItemRepository;
import br.com.controle.domain.repository.OrderRepository;
import br.com.controle.domain.repository.ProductRepository;
import br.com.controle.domain.utils.Utils;
import br.com.controle.domain.utils.interfaces.service.Services;

@Service
public class OrderService implements Services<Order> {

	@Autowired
	public OrderRepository repository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public CashRegisterService cashRegisterService;

	@Override
	public Order save(Object obj) {
		validate(obj);

		Order order = (Order) obj;
		order.setTotal(total(order));
		repository.save(order);
		order.setItems(saveItems(order,((Order) obj).getItems()));

		populateItems(order);
		return order;
	}

	private List<OrderItem> saveItems(Order order, List<OrderItem> items) {
		items.forEach(e -> e.setOrder(order));
		orderItemRepository.saveAll(items);
		return items;
	}

	private void populateItems(Order order) {
		order.getItems().forEach(e -> e.setProduct(productRepository.findById(e.getProduct().getId()).get()));
	}

	private BigDecimal total(Order obj) {
		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : obj.getItems()) {
			total = total.add(item.totalItem());
		}
		return total;
	}

	private void validate(Object obj) {
		Objects.requireNonNull(obj, MessageException.OBJECT_NOT_NULL.getValue());
		if (((Order) obj).getCashRegister() == null || ((Order) obj).getCashRegister().getId() == 0) {
			throw new BusinessException(MessageException.CASH_REGISTER_NOT_FOUND.getValue(),
					((Order) obj).getCashRegister().getId());
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if (cashRegister.isPresent()) {
				if (Utils.valueDiffZero(cashRegister.get().getTotalClosure())) {
					throw new BusinessException(MessageException.CASH_REGISTER_CLOSE.getValue(),
							cashRegister.get().getId());
				}
			} else {
				throw new BusinessException(MessageException.CASH_REGISTER_NOT_FOUND.getValue(),
						((Order) obj).getCashRegister().getId());
			}
		}
	}

	@Override
	public Order update(Object obj) {
		validate(obj);

		repository.deleteItens(((Order) obj).getIdOrder());

		Order order = (Order) obj;
		order.setTotal(total(order));
		repository.save(order);
		order.setItems(saveItems(order,((Order) obj).getItems()));

		populateItems(order);
		return order;
	}

	@Override
	public void delete(long codigo) {
	}

	@Override
	public Optional<Order> findById(long id) {
		Optional<Order> order = repository.findById(id);

		if (!order.isPresent()) {
			throw new BusinessException(MessageException.ORDER_NOT_FOUND.getValue(), id);
		}

		return order;
	}

	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		if (!repository.existsById(id)) {
			throw new BusinessException(MessageException.ORDER_NOT_FOUND.getValue(), id);
		}
		return true;
	}

	public List<Order> openOrders() {
		return repository.openOrders();
	}

	public boolean cancel(Long id) {

		if (existsById(id)) {
			return cancelOrder(id);
		}
		return false;
	}

	private boolean cancelOrder(long id) {
		return repository.cancelOrder(id);
	}

}
