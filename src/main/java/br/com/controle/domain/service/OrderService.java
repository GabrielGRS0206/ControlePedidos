package br.com.controle.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.not_found.CashRegisterException;
import br.com.controle.domain.exception.not_found.OrderNotFoundException;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.repository.OrderItemRepository;
import br.com.controle.domain.repository.OrderRepository;
import br.com.controle.domain.repository.ProductRepository;
import br.com.controle.domain.utils.SpringUtils;
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
		Objects.requireNonNull(obj, "Objeto nao pode ser null");

		if (((Order) obj).getCashRegister() == null || ((Order) obj).getCashRegister().getId() == 0) {
			throw new CashRegisterException("Deve ser informado um id de caixa");
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if (cashRegister.isPresent()) {
				if (SpringUtils.valueDiffZero(cashRegister.get().getTotalClosure())) {
					throw new CashRegisterException(
							"Caixa com código " + cashRegister.get().getId() + " já está com valor de fechamento");
				}
			} else {
				throw new CashRegisterException(((Order) obj).getCashRegister().getId());
			}
		}

		List<OrderItem> items = ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order porder = (Order) obj;
		porder.setTotal(total);
		repository.save(porder);

		items.forEach(e -> e.setId(null));
		items.forEach(e -> e.setOrder(porder));

		orderItemRepository.saveAll(items);

		items.forEach(e -> e.setProduct(productRepository.findById(e.getProduct().getId()).get()));
		porder.setItems(items);

		return porder;
	}

	@Override
	public Order update(Object obj) {

		Objects.requireNonNull(obj, "Objeto nao pode ser null");

		if (((Order) obj).getCashRegister() == null || ((Order) obj).getCashRegister().getId() == 0) {
			throw new CashRegisterException("Deve ser informado um id de caixa");
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if (cashRegister.isPresent()) {
				if (SpringUtils.valueDiffZero(cashRegister.get().getTotalClosure())) {
					throw new CashRegisterException(
							"Caixa com código " + cashRegister.get().getId() + " já está com valor de fechamento");
				}
			} else {
				throw new CashRegisterException(((Order) obj).getCashRegister().getId());
			}
		}

		repository.deleteItens(((Order) obj).getIdOrder());
		List<OrderItem> items = ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order order = (Order) obj;
		order.setTotal(total);
		items.forEach(e -> e.setId(null));
		repository.save(order);

		items.forEach(e -> e.setOrder(order));

		orderItemRepository.saveAll(items);

		items.forEach(e -> e.setProduct(productRepository.findById(e.getProduct().getId()).get()));
		order.setItems(items);

		return order;
	}

	@Override
	public void delete(long codigo) {
	}

	@Override
	public Optional<Order> findById(long id) {
		Optional<Order> order = repository.findById(id);

		if (!order.isPresent()) {
			throw new OrderNotFoundException(id);
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
			throw new OrderNotFoundException(id);
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
